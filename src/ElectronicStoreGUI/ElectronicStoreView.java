package ElectronicStoreGUI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

//main view class
public class ElectronicStoreView extends Pane{

    private ListPane popularList;
    private ListPane stockList;
    private ListPane cartList;

    private ButtonsPane buttons;

    private TextField numSalesText;
    private TextField revenueText;
    private TextField perSaleText;

    ElectronicStore model;

    public ElectronicStoreView(ElectronicStore initModel) {

        model = initModel;

        //uses using ListPane class to produce three lists we need, taking in pane width, pane length, and text to use in label as constructors
        popularList = new ListPane(300, 270, "Most Popular Items:");
        popularList.relocate(0,170);

        stockList = new ListPane(350, 440, "Store Stock:");
        stockList.relocate(300, 0);

        cartList = new ListPane(350, 440, ("Current Cart: $" + model.getCartTotal()));
        cartList.relocate(650,0);

        //just separated the buttons all into one pane for fun
        buttons = new ButtonsPane();
        buttons.relocate(0,440);

        //added rest of the elements in the main view class
        Label label1 = new Label("Store Summary");
        label1.setPrefSize(280, 20);
        label1.relocate(10,20);
        label1.setAlignment(Pos.CENTER);

        Label label2 = new Label("# Sales:");
        label2.setPrefSize(90, 20);
        label2.relocate(10,60);
        label2.setAlignment(Pos.CENTER_RIGHT);

        numSalesText = new TextField();
        numSalesText.setPrefSize(160, 20);
        numSalesText.relocate(100, 60);
        numSalesText.setAlignment(Pos.CENTER_LEFT);
        numSalesText.setEditable(false);

        Label label3 = new Label("Revenue:");
        label3.setPrefSize(90, 20);
        label3.relocate(10,100);
        label3.setAlignment(Pos.CENTER_RIGHT);

        revenueText = new TextField();
        revenueText.setPrefSize(160, 20);
        revenueText.relocate(100, 100);
        revenueText.setAlignment(Pos.CENTER_LEFT);
        revenueText.setEditable(false);

        Label label4 = new Label("$ / Sale:");
        label4.setPrefSize(90, 20);
        label4.relocate(10,140);
        label4.setAlignment(Pos.CENTER_RIGHT);

        perSaleText = new TextField();
        perSaleText.setPrefSize(160, 20);
        perSaleText.relocate(100, 140);
        perSaleText.setAlignment(Pos.CENTER_LEFT);
        perSaleText.setEditable(false);

        getChildren().addAll(popularList, stockList, cartList, buttons, label1, label2, label3, label4, numSalesText, revenueText, perSaleText);
    }

    public void setModel(ElectronicStore model){
        this.model = model;
    }

    public ListPane getStockList() {
        return stockList;
    }

    public ListPane getCartList() {
        return cartList;
    }

    public ButtonsPane getButtons() {
        return buttons;
    }

    public void update(){

        //update popular products
        ObservableList<String> currentPopularList = FXCollections.observableArrayList();

        for (int i=0; i<model.getPopular().length; i++) {
            currentPopularList.add(model.getPopular()[i].toString());
        }
        popularList.getList().setItems(currentPopularList);

        //update cartList
        ObservableList<String> currentCartList = FXCollections.observableArrayList();

        for (int i=0; i<model.getCart().size(); i++) {
            currentCartList.add(model.getCart().get(i).toString());
        }
        cartList.getList().setItems(currentCartList);

        //update total price of things in the cart
        cartList.getLabel().setText("Current Cart: $" + model.getCartTotal());

        //show products currently in stock
        ObservableList<String> currentStockList = FXCollections.observableArrayList();

        for (int i=0; i<model.getCurProducts(); i++) {
            if (model.getStock()[i].getStockQuantity() > 0) {
                currentStockList.add(model.getStock()[i].toString());
            }
        }

        stockList.getList().setItems(currentStockList);

        //update number of sales
        numSalesText.setText(model.getSold() + "");

        //update revenue
        String revString = String.format("%.2f", model.getRevenue());
        revenueText.setText(revString);

        //update revenue per sale
        if (model.getSold() > 0) {
            String revPerSaleString = String.format("%.2f", model.getRevPerSale());
            perSaleText.setText(revPerSaleString);
        }
        else {
            perSaleText.setText("N/A");
        }

        //if an item in stockList is selected, enable addButton
        int selection = stockList.getList().getSelectionModel().getSelectedIndex();
        if (selection == -1) {
            buttons.getAddButton().setDisable(true);
        }
        else {
            buttons.getAddButton().setDisable(false);
        }

        //if an item in cartList is selected, enable removeButton
        int selection1 = cartList.getList().getSelectionModel().getSelectedIndex();
        if (selection1 == -1) {
            buttons.getRemoveButton().setDisable(true);
        }
        else {
            buttons.getRemoveButton().setDisable(false);
        }

        //if there is an item in cartList, enable completeButton
        if (model.getCart().size() > 0) {
            buttons.getCompleteButton().setDisable(false);
        }
        else{
            buttons.getCompleteButton().setDisable(true);
        }
    }
}

package ElectronicStoreGUI;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

//controller class
public class ElectronicStoreApp extends Application {
    private ElectronicStore model;
    private ElectronicStoreView view;

    @Override
    public void start(Stage primaryStage) {
        model = ElectronicStore.createStore();
        view = new ElectronicStoreView(model);

        //connect addButton to action
        view.getButtons().getAddButton().setOnAction(actionEvent -> handleAdd());

        //connect resetButton to action
        view.getButtons().getResetButton().setOnAction(actionEvent -> handleReset());

        //connect removeButton to action
        view.getButtons().getRemoveButton().setOnAction(actionEvent -> handleRemove());

        //connect completeButton to action
        view.getButtons().getCompleteButton().setOnAction(actionEvent -> handleComplete());

        //when there is a click in stockList, update
        view.getStockList().getList().setOnMouseReleased(mouseEvent -> view.update());

        //where there is a click in cartList, update
        view.getCartList().getList().setOnMouseReleased(mouseEvent -> view.update());

        primaryStage.setTitle("Electronic Store Application - " + model.getName());
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(view, 1000, 500));
        primaryStage.show();

        view.update();
    }

    public static void main(String[] args) {
        launch(args);
    }

    //add selected item to the cart
    private void handleAdd() {
        model.addToCart(view.getStockList().getList().getSelectionModel().getSelectedItem());
        view.update();
    }

    //resets the model to its initial state
    private void handleReset() {
        model = ElectronicStore.createStore();
        view.setModel(model);
        view.update();
    }

    //remove selected item from the cart
    private void handleRemove() {
        model.removeFromCart(view.getCartList().getList().getSelectionModel().getSelectedIndex());
        view.update();
    }

    //complete the sale in the cart
    private void handleComplete() {
        model.completeSale();
        view.update();
    }
}

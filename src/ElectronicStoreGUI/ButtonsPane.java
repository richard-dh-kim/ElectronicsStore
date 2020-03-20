package ElectronicStoreGUI;

import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

//creates pane with buttons
public class ButtonsPane extends Pane {

    private Button resetButton;
    private Button addButton;
    private Button removeButton;
    private Button completeButton;

    public ButtonsPane() {
        Pane innerPane = new Pane();
        innerPane.setPrefSize(500,60);

        resetButton = new Button("Reset Store");
        resetButton.setPrefSize(160, 40);
        resetButton.relocate(70,0);

        addButton = new Button("Add to Cart");
        addButton.setPrefSize(160, 40);
        addButton.relocate(395,0);

        removeButton = new Button("Remove from Cart");
        removeButton.setPrefSize(160, 40);
        removeButton.relocate(660,0);

        completeButton = new Button("Complete Sale");
        completeButton.setPrefSize(160, 40);
        completeButton.relocate(830,0);

        innerPane.getChildren().addAll(resetButton, addButton, removeButton, completeButton);
        getChildren().add(innerPane);
    }

    public Button getResetButton() {
        return resetButton;
    }

    public Button getAddButton() {
        return addButton;
    }

    public Button getRemoveButton() {
        return removeButton;
    }

    public Button getCompleteButton() {
        return completeButton;
    }


}

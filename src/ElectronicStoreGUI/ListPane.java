package ElectronicStoreGUI;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;

//produces a list pane with a label on top
public class ListPane extends Pane {

    private ListView<String> List;
    private Label label;

    public ListPane(int paneWidth, int paneLength, String labelText) {
        Pane innerPane = new Pane();
        innerPane.setPrefSize(paneWidth, paneLength);

        label = new Label(labelText);
        label.setPrefSize(paneWidth-20, 20);
        label.relocate(10, 20);
        label.setAlignment(Pos.CENTER);

        List = new ListView<>();
        List.setPrefSize(paneWidth-20, paneLength-60);
        List.relocate(10,40);

        innerPane.getChildren().addAll(label, List);
        getChildren().add(innerPane);
    }

    public ListView<String> getList() {
        return List;
    }

    public Label getLabel() {
        return label;
    }
}

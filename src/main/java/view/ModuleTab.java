package view;

import controller.ModuleTabController;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;
import java.sql.SQLException;

public class ModuleTab extends Tab {

    public ModuleTab(TabPane tabPane, FXMLLoader loader) throws IOException, SQLException {
        ModuleTab moduleTab = new ModuleTab();
        loader = new FXMLLoader(getClass().getResource("/FXMLview/ModulesTab.fxml"));
        AnchorPane moduleTabContent = loader.load();
        tabPane.getTabs().add(moduleTab);
        moduleTab.setText("Modules");
        moduleTab.setContent(moduleTabContent);
        Object temp = loader.getController();
        ModuleTabController controller = (ModuleTabController) temp;
        controller.populate();
        tabPane.getSelectionModel().select(moduleTab);
    }

    public ModuleTab() {

    }
}

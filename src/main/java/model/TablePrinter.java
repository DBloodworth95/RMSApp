package model;

import javafx.print.*;
import javafx.scene.Node;

public class TablePrinter {

    public TablePrinter() {

    }

    public void print(Paper paper, Node node) {
        Printer printer = Printer.getDefaultPrinter();
        PrinterJob printerJob = PrinterJob.createPrinterJob(printer);
        PageLayout pageLayout = printerJob.getPrinter().createPageLayout(paper, PageOrientation.LANDSCAPE, 0, 0, 0, 0);
        boolean success = printerJob.printPage(pageLayout, node);
        if (success) {
            printerJob.endJob();
        }
    }
}

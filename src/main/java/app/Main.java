package app;

import interface_adapter.ViewManager;
import interface_adapter.ViewManagerModel;
import interface_adapter.failed_url.FailedURLViewModel;
import interface_adapter.url.URLController;
import interface_adapter.url.URLPresenter;
import interface_adapter.url.URLViewModel;
import use_case.select_for_conversion.url.URLInputBoundary;
import use_case.select_for_conversion.url.URLInteractor;
import use_case.select_for_conversion.url.URLOutputBoundary;
import view.FailedURLView;
import view.URLView;

import javax.swing.*;
import java.awt.*;

/**
 * Main application entry point.
 */
public class Main {
    public static void main(String[] args) {
        // Create the main application window
        final JFrame application = new JFrame("URL Validator Application");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        final CardLayout cardLayout = new CardLayout();
        final JPanel views = new JPanel(cardLayout);
        application.add(views);

        // Create the View Manager
        final ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        // Create the View Models
        final URLViewModel urlViewModel = new URLViewModel();
        final FailedURLViewModel failedURLViewModel = new FailedURLViewModel();

        // Create the Views
        final URLView urlView = new URLView(urlViewModel);
        views.add(urlView, urlView.getViewName());

        final FailedURLView failedURLView = new FailedURLView(
                failedURLViewModel,
                urlViewModel,
                viewManagerModel
        );
        views.add(failedURLView, failedURLView.getViewName());

        // Create the Use Case components
        final URLOutputBoundary urlPresenter = new URLPresenter(
                urlViewModel,
                failedURLViewModel,
                viewManagerModel
        );

        final URLInputBoundary urlInteractor = new URLInteractor(urlPresenter);
        final URLController urlController = new URLController(urlInteractor);

        // Set the controller in the view
        urlView.setURLController(urlController);

        // Set the initial view
        viewManagerModel.setActiveView(urlView.getViewName());
        viewManagerModel.firePropertyChanged();

        // Display the application
        application.pack();
        application.setLocationRelativeTo(null);
        application.setVisible(true);
    }
}
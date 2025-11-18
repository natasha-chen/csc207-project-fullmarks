package app;//package app;
//
//import javax.swing.*;
//import java.awt.*;
//
//public class AppBuilder {
//    private final JPanel cardPanel = new JPanel();
//    private final CardLayout cardLayout = new CardLayout();
//    //TODO: figure out Entity Factory
//    final viewManagerModel viewManagerModel = new ViewManagerModel();
//    ViewManager viewManager = new ViewManager(cardPanel, cardLayout, viewManagerModel);
//
//    private DownloadView downloadView;
//    private ConvertView convertView;
//    private SavetoView savetoView;
//    private ProgressView progressView;
//    private ExcludeView excludeView;
//
//}

import interface_adapter.ViewManagerModel;
import interface_adapter.url.URLController;
import interface_adapter.url.URLViewModel;
import use_case.select_for_conversion.url.URLInputBoundary;
import use_case.select_for_conversion.url.URLOutputBoundary;
import view.URLView;
import view.ViewManager;

import javax.swing.*;
import java.awt.*;

public class AppBuilder {
    private final JPanel cardPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();
    //final UserFactory userFactory = new UserFactory();
    final ViewManagerModel viewManagerModel = new ViewManagerModel();
    ViewManager viewManager = new ViewManager(cardPanel, cardLayout, viewManagerModel);

    private URLView urlView;
    private URLViewModel urlViewModel;

    public AppBuilder() {cardPanel.setLayout(cardLayout);}

    public AppBuilder addURLView() {
    urlViewModel = new URLViewModel();
    urlView = new URLView(urlViewModel);
    cardPanel.add(urlView, urlView.getName());
    return this;
    }

    public JFrame build() {
        final JFrame application = new JFrame("URL Example");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        application.add(cardPanel);

        viewManagerModel.setState(urlView.getName());
        viewManagerModel.firePropertyChange();

        return application;
    }
}


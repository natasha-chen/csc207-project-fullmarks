package app;

import entity.VideoFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.select_for_conversion.*;
// import other use cases here
import use_case.select_for_conversion.*;
import view.*;

import javax.swing.*;
import java.awt.*;

public class AppBuilder {
    private final JPanel cardPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();
    final VideoFactory videoFactory = new VideoFactory();
    final ViewManagerModel viewManagerModel = new ViewManagerModel();
    ViewManager viewManager = new ViewManager(cardPanel, cardLayout, viewManagerModel);

    // private AccountMenuView accountMenuView;
    // private AccountMenuViewModel accountMenuViewModel;
    // private SignupView signupView;
    // private SignupViewModel signupViewModel;
    // private LoginView loginView;
    // private LoginViewModel loginViewModel;
    // private UrlView urlView;
    // private UrlViewModel urlViewModel;
    // private FolderDestinationView folderDestinationView;
    // private FolderDestinationViewModel folderDestinationViewModel;
    // private ConvertView convertView;
    // private ConvertViewModel convertViewModel;
    private SelectForConversionView selectForConversionView;
    private SelectForConversionViewModel selectForConversionViewModel;
    // private ProgressView progressView;
    // private ProgressViewModel progressViewModel
    // private CreatePlaylistView createPlaylistView;
    // private CreatePlaylistViewModel createPlaylistViewModel;

    public AppBuilder() {
        cardPanel.setLayout(cardLayout);
    }

    public AppBuilder addSelectForConversionView() {
        selectForConversionViewModel = new SelectForConversionViewModel();
        selectForConversionView = new SelectForConversionView(selectForConversionViewModel);
        cardPanel.add(selectForConversionView, selectForConversionView.getViewName());
        return this;
    }

    public AppBuilder addSelectForConversionUseCase() {
        final SelectForConversionOutputBoundary selectForConversionPresenter = new SelectForConversionPresenter(
                selectForConversionViewModel, viewManagerModel);
        final SelectForConversionInputBoundary selectForConversionInteractor = new SelectForConversionInteractor(
                selectForConversionPresenter);
        SelectForConversionController selectForConversionController = new SelectForConversionController(
                selectForConversionInteractor);
        selectForConversionView.setSelectForConversionController(selectForConversionController);
        return this;
    }

    public JFrame build() {
        final JFrame application = new JFrame("Test: Select For Conversion");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        application.add(cardPanel);

        viewManagerModel.setState(selectForConversionView.getViewName());
        viewManagerModel.firePropertyChange();

        return application;
    }
}

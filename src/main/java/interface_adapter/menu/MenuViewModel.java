package interface_adapter.menu;

import interface_adapter.ViewModel;

public class MenuViewModel extends ViewModel {
    public static final String VIEW_NAME = "menu";

    private MenuState state = new MenuState();

    public MenuViewModel() {}

    public MenuState getState() {
        return state;
    }

    public void setState(MenuState state) {
        this.state = state;
    }

    @Override
    public String getViewName() {
        return VIEW_NAME;
    }
}

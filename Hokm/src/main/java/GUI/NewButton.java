package GUI;

import Game.Card;
import Game.CardSuit;
import Game.Rank;

import javax.swing.*;
import java.awt.*;

public class NewButton extends JButton {
    private String iconAddress;
    public NewButton(Card card){
        this.iconAddress = "images\\" + card.getSuit().getName() + "." + card.getRank().getName() + ".jpg" ;
        ImageIcon test = new ImageIcon(this.iconAddress);
        int width = test.getIconWidth();
        int height = test.getIconHeight();
        setPreferredSize(new Dimension(width,height));
        setIcon(test);
    }
    public void setIconAddress(String iconAddress) {
        this.iconAddress = iconAddress;
    }
    public String getIconAddress() {
        return iconAddress;
    }
}
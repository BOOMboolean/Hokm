package GUI;

import Game.Card;
import Game.CardSuit;
import Game.Rank;

import javax.swing.*;
import java.awt.*;

public class NewButton extends JButton {
    public NewButton(Card card){
        String iconAddress = "images/" + card.getSuit().getName() + "." + card.getRank().getName() + ".jpg" ;
        ImageIcon test = new ImageIcon(iconAddress);
        int width = test.getIconWidth();
        int height = test.getIconHeight();
        setPreferredSize(new Dimension(width,height));
        setIcon(test);
    }
}

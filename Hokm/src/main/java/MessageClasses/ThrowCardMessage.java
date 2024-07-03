package MessageClasses;

import Game.Card;

public class ThrowCardMessage extends ClientMessage{
    ThrowCardMessage(){
        type=ClientMessageType.throwCard;
    }
    Card card;
}

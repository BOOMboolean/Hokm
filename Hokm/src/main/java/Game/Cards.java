package Game;

public class Cards {

    enum ValueName{ //doing these to make everything look better
        Two (2), //started from 2 instead of 0 to avoid confusion. might change this later
        Three (3),
        Four (4),
        Five (5),
        Six (6),
        Seven (7),
        Eight (8),
        Nine (9),
        Jack (10),
        Queen (11),
        King (12),
        Ace (13);

        private final int value;
        ValueName(int value){this.value = value;}

        public int getValue() {return this.value;}

        public static ValueName getValueName(int value){
            for(ValueName desiredValue : ValueName.values()){
                if(desiredValue.getValue() == value){
                    return desiredValue;
                }
            }
            return null; //might have to change this
        }


    }

}

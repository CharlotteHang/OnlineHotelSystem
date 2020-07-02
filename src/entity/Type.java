package entity;

public enum Type{
    LUXURY1,LUXURY2,LUXURY3,LUXURY4,DEFAULT;
    public String toString() {
        switch (this) {
            case LUXURY1:
                return "LUXURY1";
            case LUXURY2:
                return "LUXURY2";
            case  LUXURY3:
                return "LUXURY3";
            case  LUXURY4:
                return "LUXURY4";
            default :
                return "DEFAULT";
        }
    }
}
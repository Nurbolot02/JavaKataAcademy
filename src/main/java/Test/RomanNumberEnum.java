package Test;

public enum RomanNumberEnum {
    I(1), II(2), III(3), IV(4), V(5), VI(6), VII(7), VIII(8), IX(9), X(10);
    private int number;
    RomanNumberEnum(int number) {
        this.number = number;
    }
    public int getTranslation(){
        return number;
    }
}

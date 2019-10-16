public class OffByN implements CharacterComparator{
    int charDis;

    //construct method's signature does not include "static" and "void"
    public OffByN(int dis){
        charDis = dis;
    }

    public boolean equalChars(char x, char y){
        int charDiff = x-y;
        return ((x-y)==charDis || (y-x)==charDis)?(true):(false);
    }

}

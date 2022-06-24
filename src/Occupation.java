public enum Occupation {
    BANKER(160000), CARPENTER(80000), FARMER(40000);

    public int getStartingCash() {
        return startingCash;
    }

    int startingCash;
    Occupation(int startingCash){
        this.startingCash = startingCash;
    }
        
}

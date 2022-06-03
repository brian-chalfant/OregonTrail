public enum Occupation {
    BANKER(1600), CARPENTER(800), FARMER(400);

    public int getStartingCash() {
        return startingCash;
    }

    int startingCash;
    Occupation(int startingCash){
        this.startingCash = startingCash;
    }
        
}







public class TSTNode {
    char character;
    boolean isEndOfWord;
    TSTNode left, mid, right;

    // Node in the TST
    public TSTNode(char c) {
        this.character = c;
        isEndOfWord = false;
        this.left = null;
        this.mid = null;
        this.right = null;
    }
}

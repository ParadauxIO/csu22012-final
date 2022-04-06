package io.paradaux.busmanagement.data.string;

public class TernarySearchTree {

    private TSTNode root;

    public TernarySearchTree() {
        root = null;
    }

    public TSTNode insert(String str) {
        root = insert(this.root, str.toCharArray(), 0);
        return root;
    }

    private TSTNode insert(TSTNode node, char[] letters, int i) {
        char letter = letters[i];

        if (node == null) {
            node = new TSTNode(letter);
        }

        if (letter < node.getCharacter()) {
            node.setLeft(insert(node.getLeft(), letters, i));
        } else if (letter > node.getCharacter()) {
            node.setRight(insert(node.getRight(), letters, i));
        } else {
            if (i + 1 < letters.length) {
                node.setCenter(insert(node.getCenter(), letters, i+1));
            }
        }

        return node;
    }


    public boolean contains(String target) {
        if (target == null) {
            return false;
        }

        return contains(root, target.toCharArray(), 0);
    }

    private boolean contains(TSTNode node, char[] letters, int i) {
        if (node == null) {
            return false;
        }

        char letter = letters[i];

        if (letter < node.getCharacter()) {
            return contains(node.getLeft(), letters, i);
        } else if (letter > node.getCharacter()) {
            return contains(node.getRight(), letters, i);
        } else {
            if (i == letters.length - 1) {
                return node.isEnd();
            }

            return contains(node.getCenter(), letters, i);
        }
    }


    public String[] search(String word) {
        if (word == null || word.charAt(0) == ' ') {
            return null;
        }

        StringBuilder sb = new StringBuilder();

        TSTNode root = search(this.root, word.toCharArray(), 0);
        generateSuggestions(root, "", sb, word);
        if (sb.length() < 1) {
            return new String[]{};
        }

        return sb.toString().split("\n");
    }

    private TSTNode search(TSTNode node, char[] letters, int i) {
        if (node == null) {
            return null;
        }

        if (letters[i] < node.getCharacter()) {
            return search(node.getLeft(), letters, i);
        } else if (letters[i] > node.getCharacter()) {
            return search(node.getRight(), letters, i);
        } else {
            if (i == letters.length - 1) {
                return node;
            } else {
                return search(node.getCenter(), letters, i + 1);
            }
        }
    }


    private void generateSuggestions(TSTNode node, String str, StringBuilder sb, String pattern) {
        if (node == null) {
            return;
        }

        generateSuggestions(node.getLeft(), str, sb, pattern);
        str = str + node.getCenter();

        if (node.isEnd()) {
            if (pattern.length() == 1) {
                if (pattern.equals(str.substring(0, 1))) {
                    sb.append(pattern)
                            .append(str.substring(1))
                            .append('\n');
                }
            } else {
                sb.append(pattern)
                        .append(str.substring(1))
                        .append('\n');
            }
        }

        generateSuggestions(node.getCenter(), str, sb, pattern);
        str = str.substring(0, str.length() - 1);
        generateSuggestions(node.getRight(), str, sb, pattern);
    }

    private static class TSTNode {
        private final Character character;
        private boolean isEnd;

        private TSTNode left, center, right;

        public TSTNode(Character character) {
            this(character, false);
        }

        public TSTNode(Character character, boolean isEnd) {
            this.character = character;
            this.isEnd = isEnd;
        }

        boolean isLeaf() {
            return left == null && right == null && center == null;
        }

        public Character getCharacter() {
            return character;
        }

        public boolean isEnd() {
            return isEnd;
        }

        public TSTNode getLeft() {
            return left;
        }

        public TSTNode getCenter() {
            return center;
        }

        public TSTNode getRight() {
            return right;
        }

        public TSTNode setEnd(boolean end) {
            isEnd = end;
            return this;
        }

        public TSTNode setLeft(TSTNode left) {
            this.left = left;
            return this;
        }

        public TSTNode setCenter(TSTNode center) {
            this.center = center;
            return this;
        }

        public TSTNode setRight(TSTNode right) {
            this.right = right;
            return this;
        }
    }

}

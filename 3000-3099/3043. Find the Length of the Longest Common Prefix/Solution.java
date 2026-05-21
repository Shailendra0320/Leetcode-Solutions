class Solution {
    private static Node root;
    
    Solution() {
        root = new Node();
    }

    class Node {
        Node[] children = new Node[10];
        boolean isEnd = false;
        
        Node() {}

        boolean containsKey(char ch) {
            return (children[ch - '0'] != null);
        }

        Node getNode(char ch) {
            return children[ch - '0'];
        }

        void putNode(char ch, Node node) {
            children[ch - '0'] = node;
        }

        void setEnd() {
            isEnd = true;
        }

        boolean isLast() {
            return isEnd;
        }

        void insert(String word) {
            Node node = root;
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                if (!node.containsKey(ch)) {
                    node.putNode(ch, new Node());
                }
                node = node.getNode(ch);
            }
            node.setEnd();
        }

        int findCommonPrefixLength(String word) {
            Node node = root;
            int count = 0;
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                if (node.containsKey(ch)) {
                    node = node.getNode(ch);
                    count++;
                } else {
                    break;
                }
            }
            return count;
        }
    }

    public int longestCommonPrefix(int[] arr1, int[] arr2) {
        root = new Node();
        Node trie = new Node();
        int maxLength = 0;

        for (int num : arr2) {
            trie.insert(String.valueOf(num));
        }

        for (int num : arr1) {
            int length = trie.findCommonPrefixLength(String.valueOf(num));
            maxLength = Math.max(maxLength, length);
        }

        return maxLength;
    }
}
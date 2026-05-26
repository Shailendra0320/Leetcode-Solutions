//Approach-1 (Using Frequency Arrays)
//T.C : O(n)
//S.C : O(1)

class Solution {

    public int numberOfSpecialChars(String word) {

        boolean[] lowercase = new boolean[26];

        boolean[] uppercase = new boolean[26];

        for (char c : word.toCharArray()) {

            if (c >= 'a' && c <= 'z') {

                lowercase[c - 'a'] = true;
            }
            else if (c >= 'A' && c <= 'Z') {

                uppercase[c - 'A'] = true;
            }
        }

        int count = 0;

        for (int i = 0; i < 26; i++) {

            if (lowercase[i] && uppercase[i]) {

                count++;
            }
        }

        return count;
    }
}


/*
//Approach-2 (Using HashSet)
//T.C : O(n)
//S.C : O(n)

class Solution {

    public int numberOfSpecialChars(String word) {

        Set<Character> lower = new HashSet<>();

        Set<Character> upper = new HashSet<>();

        for (char ch : word.toCharArray()) {

            if (Character.isLowerCase(ch)) {

                lower.add(ch);
            }
            else if (Character.isUpperCase(ch)) {

                upper.add(Character.toLowerCase(ch));
            }
        }

        int count = 0;

        for (char ch : lower) {

            if (upper.contains(ch)) {

                count++;
            }
        }

        return count;
    }
}
*/


/*
//Approach-3 (Brute Force)
//T.C : O(n^2)
//S.C : O(1)

class Solution {

    public int numberOfSpecialChars(String word) {

        int count = 0;

        boolean[] arr = new boolean[26];

        Arrays.fill(arr, false);

        for (int i = 0; i < word.length(); i++) {

            char ch = word.charAt(i);

            if (Character.isUpperCase(ch)) {

                continue;
            }
            else {

                char upper = Character.toUpperCase(ch);

                for (int j = 0; j < word.length(); j++) {

                    if (word.charAt(j) == upper && !arr[ch - 'a']) {

                        count++;

                        arr[ch - 'a'] = true;
                    }
                }
            }
        }

        return count;
    }
}
*/
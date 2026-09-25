import java.util.*;

//Approach-1 (Recursive Descent Parser)
//T.C : Output-sensitive, worst-case exponential
//S.C : O(n) recursion + output space

class Solution {
    private String expression;
    private int index;

    public List<String> braceExpansionII(String expression) {
        this.expression = expression;
        this.index = 0;

        Set<String> result = parseExpression();

        List<String> answer = new ArrayList<>(result);
        Collections.sort(answer);

        return answer;
    }

    private Set<String> parseExpression() {
        Set<String> result = parseTerm();

        while (index < expression.length() && expression.charAt(index) == ',') {
            index++;
            result.addAll(parseTerm());
        }

        return result;
    }

    private Set<String> parseTerm() {
        Set<String> result = new HashSet<>();
        result.add("");

        while (index < expression.length()) {
            char ch = expression.charAt(index);

            if (ch == '}' || ch == ',') {
                break;
            }

            Set<String> factor = parseFactor();
            result = concatenate(result, factor);
        }

        return result;
    }

    private Set<String> parseFactor() {
        char ch = expression.charAt(index);

        if (ch == '{') {
            index++;

            Set<String> result = parseExpression();

            index++;
            return result;
        }

        index++;

        Set<String> result = new HashSet<>();
        result.add(String.valueOf(ch));

        return result;
    }

    private Set<String> concatenate(
        Set<String> first,
        Set<String> second
    ) {
        Set<String> result = new HashSet<>();

        for (String a : first) {
            for (String b : second) {
                result.add(a + b);
            }
        }

        return result;
    }
}


//Approach-2 (Stack-Based Set Evaluation)
//T.C : Output-sensitive, worst-case exponential
//S.C : O(n) stack + output space

class Solution2 {
    static class Frame {
        Set<String> union = new HashSet<>();
        Set<String> product = new HashSet<>(Collections.singleton(""));
    }

    public List<String> braceExpansionII(String expression) {
        Deque<Frame> stack = new ArrayDeque<>();
        stack.push(new Frame());

        for (char ch : expression.toCharArray()) {

            if (ch == '{') {
                stack.push(new Frame());
            }

            else if (ch == ',') {
                Frame frame = stack.peek();

                frame.union.addAll(frame.product);

                frame.product = new HashSet<>();
                frame.product.add("");
            }

            else if (ch == '}') {
                Frame frame = stack.pop();

                frame.union.addAll(frame.product);

                Frame parent = stack.peek();

                parent.product = concatenate(
                    parent.product,
                    frame.union
                );
            }

            else {
                Set<String> letter = new HashSet<>();
                letter.add(String.valueOf(ch));

                Frame frame = stack.peek();

                frame.product = concatenate(
                    frame.product,
                    letter
                );
            }
        }

        Frame root = stack.pop();
        root.union.addAll(root.product);

        List<String> answer = new ArrayList<>(root.union);
        Collections.sort(answer);

        return answer;
    }

    private Set<String> concatenate(
        Set<String> first,
        Set<String> second
    ) {
        Set<String> result = new HashSet<>();

        for (String a : first) {
            for (String b : second) {
                result.add(a + b);
            }
        }

        return result;
    }
}
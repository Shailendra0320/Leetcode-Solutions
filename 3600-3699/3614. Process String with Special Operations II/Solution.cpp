class Solution {
public:

    char processStr(string s, long long k) {

        int n = s.size();

        vector<long long> length(n);

        long long currentLength = 0;

        for (int i = 0; i < n; i++) {

            char current = s[i];

            if (current == '*') {

                currentLength = max(0LL, currentLength - 1);
            }
            else if (current == '#') {

                currentLength =
                    min(
                        currentLength * 2,
                        (long long)2e15
                    );
            }
            else if (current != '%') {

                currentLength++;
            }

            length[i] = currentLength;
        }

        if (k >= length[n - 1]) {
            return '.';
        }

        for (int i = n - 1; i >= 0; i--) {

            char current = s[i];

            if (current == '#') {

                long long previousLength =
                    (i > 0 ? length[i - 1] : 0);

                if (k >= previousLength) {
                    k -= previousLength;
                }
            }
            else if (current == '%') {

                long long previousLength =
                    (i > 0 ? length[i - 1] : 0);

                k = previousLength - 1 - k;
            }
            else if (current != '*') {

                if (length[i] - 1 == k) {
                    return current;
                }
            }
        }

        return '.';
    }
};
// Approach-1 (One Pass Digit Simulation)
// T.C : O(d)
// S.C : O(1)

class Solution
{
public:
  long long sumAndMultiply(int n)
  {

    int digitSum = 0;

    long long newNumber = 0;

    long long place = 1;

    while (n > 0)
    {

      int digit = n % 10;

      if (digit != 0)
      {

        digitSum += digit;

        newNumber +=
            1LL * digit * place;

        place *= 10;
      }

      n /= 10;
    }

    return newNumber * digitSum;
  }
};

/*
//Approach-2 (List Simulation)
//T.C : O(d)
//S.C : O(d)

class Solution {
public:

    long long sumAndMultiply(int n) {

        vector<int> digits;

        while (n > 0) {

            if (n % 10 != 0) {

                digits.push_back(
                    n % 10
                );
            }

            n /= 10;
        }

        long long digitSum = 0;

        for (int digit : digits) {

            digitSum += digit;
        }

        long long newNumber = 0;

        for (
            int i = digits.size() - 1;
            i >= 0;
            i--
        ) {

            newNumber =
                newNumber * 10 +
                digits[i];
        }

        return newNumber * digitSum;
    }
};
*/

/*
//Approach-3 (String Simulation)
//T.C : O(d)
//S.C : O(d)

class Solution {
public:

    long long sumAndMultiply(int n) {

        string number =
            to_string(n);

        string result = "";

        long long digitSum = 0;

        for (char current : number) {

            if (current == '0') {

                continue;
            }

            result += current;

            digitSum +=
                current - '0';
        }

        long long newNumber =
            stoll(result);

        return newNumber * digitSum;
    }
};
*/
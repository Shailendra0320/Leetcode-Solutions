// Approach-1 (Frequency HashMap) - MAIN SOLUTION
// T.C : O(n log log M)
// S.C : O(n)

class Solution
{
public:
  int maximumLength(vector<int> &nums)
  {

    unordered_map<long long, int> count;

    for (int num : nums)
    {

      count[(long long)num]++;
    }

    int answer = 1;

    if (count.count(1))
    {

      int ones = count[1];

      answer =
          max(
              answer,
              ones % 2 ? ones : ones - 1);
    }

    for (auto &[value, freq] : count)
    {

      if (value == 1)
        continue;

      int length = 0;

      long long current = value;

      while (
          count.count(current) &&
          count[current] >= 2)
      {

        length += 2;

        current *= current;
      }

      if (count.count(current))
        length++;
      else
        length--;

      answer =
          max(answer, length);
    }

    return answer;
  }
};

/*
//Approach-2 (Chain Simulation)
//T.C : O(n log log M)
//S.C : O(n)

class Solution {
public:

    int maximumLength(vector<int>& nums) {

        unordered_map<long long,int> frequency;

        unordered_set<long long> values;

        for(int num : nums){

            frequency[num]++;

            values.insert(num);
        }

        int answer = 1;

        if(frequency.count(1)){

            int ones = frequency[1];

            answer =
                max(
                    answer,
                    ones % 2 ? ones : ones - 1
                );
        }

        for(long long start : values){

            if(start == 1)
                continue;

            vector<long long> chain;

            long long current = start;

            while(values.count(current)){

                chain.push_back(current);

                current *= current;
            }

            int length = 0;

            for(int i=0;i<chain.size();i++){

                if(i+1<chain.size()){

                    if(frequency[chain[i]]>=2){

                        length+=2;

                    }else{

                        break;
                    }

                }else{

                    length++;
                }
            }

            answer=max(answer,length);
        }

        return answer;
    }
};

//Approach-3 (Greedy Frequency Expansion)
//T.C : O(n log log M)
//S.C : O(n)

class Solution {
public:

    int maximumLength(vector<int>& nums) {

        unordered_map<long long,int> count;

        for(int num : nums){

            count[(long long)num]++;
        }

        int oneCount = count[1];

        int answer =
            oneCount & 1
            ? oneCount
            : oneCount - 1;

        count.erase(1);

        for(auto &[start,freq] : count){

            int length = 0;

            long long current = start;

            while(
                count.count(current)
                &&
                count[current] > 1
            ){

                length += 2;

                current *= current;
            }

            answer =
                max(
                    answer,
                    length +
                    (
                        count.count(current)
                        ? 1
                        : -1
                    )
                );
        }

        return answer;
    }
};
*/
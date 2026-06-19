//Approach-1 (Running Prefix Sum)
//T.C : O(n)
//S.C : O(1)

class Solution {

    public int largestAltitude(int[] gain) {

        int currentAltitude = 0;

        int maximumAltitude = 0;

        for (int change : gain) {

            currentAltitude += change;

            maximumAltitude =
                Math.max(
                    maximumAltitude,
                    currentAltitude
                );
        }

        return maximumAltitude;
    }
}


/*
//Approach-2 (Prefix Sum Array)
//T.C : O(n)
//S.C : O(n)

class Solution {

    public int largestAltitude(int[] gain) {

        int n = gain.length;

        int[] altitude = new int[n + 1];

        int maximumAltitude = 0;

        for (int i = 0; i < n; i++) {

            altitude[i + 1] =
                altitude[i] + gain[i];

            maximumAltitude =
                Math.max(
                    maximumAltitude,
                    altitude[i + 1]
                );
        }

        return maximumAltitude;
    }
}
*/
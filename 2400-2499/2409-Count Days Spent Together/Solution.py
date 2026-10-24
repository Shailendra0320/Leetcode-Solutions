#Approach-1 (Day-of-Year + Interval Intersection)
#T.C : O(1)
#S.C : O(1)

class Solution:
    def day_of_year(self, date: str) -> int:
        month = int(date[:2])
        day = int(date[3:])

        prefix = [
            0, 31, 59, 90, 120, 151,
            181, 212, 243, 273, 304, 334
        ]

        return prefix[month - 1] + day

    def countDaysTogether(
        self,
        arriveAlice: str,
        leaveAlice: str,
        arriveBob: str,
        leaveBob: str
    ) -> int:
        start_alice = self.day_of_year(arriveAlice)
        end_alice = self.day_of_year(leaveAlice)
        start_bob = self.day_of_year(arriveBob)
        end_bob = self.day_of_year(leaveBob)

        start = max(start_alice, start_bob)
        end = min(end_alice, end_bob)

        return max(0, end - start + 1)


#Approach-2 (Enumerate Every Calendar Day)
#T.C : O(365) = O(1)
#S.C : O(1)

class Solution2:
    def day_of_year(self, date: str) -> int:
        month = int(date[:2])
        day = int(date[3:])

        prefix = [
            0, 31, 59, 90, 120, 151,
            181, 212, 243, 273, 304, 334
        ]

        return prefix[month - 1] + day

    def countDaysTogether(
        self,
        arriveAlice: str,
        leaveAlice: str,
        arriveBob: str,
        leaveBob: str
    ) -> int:
        start_alice = self.day_of_year(arriveAlice)
        end_alice = self.day_of_year(leaveAlice)
        start_bob = self.day_of_year(arriveBob)
        end_bob = self.day_of_year(leaveBob)

        answer = 0

        for day in range(1, 366):
            if (
                start_alice <= day <= end_alice
                and start_bob <= day <= end_bob
            ):
                answer += 1

        return answer
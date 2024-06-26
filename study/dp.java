
import java.util.ArrayList;
import java.util.List;
// 동적계획법

/*
https://leetcode.com/problems/counting-bits/description/ 

338. Counting Bits

*/

class Solution1_1 {
    public int[] countBits(int n) {
          // 결과를 저장할 배열 생성
        int[] ans = new int[n + 1];
        
        // 0부터 n까지 반복하면서 각 숫자의 1의 개수를 센다
        for (int i = 0; i <= n; i++) {
            ans[i] = Integer.bitCount(i);
        }
        return ans;
    }
}

/*
countBits 메서드는 입력으로 정수 n을 받습니다.
결과를 저장할 정수 배열 ans를 생성합니다. 
이 배열의 길이는 n + 1입니다.
for 루프를 사용하여 0부터 n까지 반복합니다.
각 반복마다 현재 숫자의 이진수 표현에서 1의 개수를 Integer.bitCount(i) 메서드를 사용하여 셉니다.
그 값을 배열 ans의 해당 인덱스에 저장합니다.
반복이 끝난 후, 완성된 배열 ans를 반환합니다.
*/


/* 동적계획법 풀이  */
/*
동적 계획법의 타뷸레이션 방식을 사용하여 
이전에 계산된 결과를 재사용함으로써 효율적으로 문제를 해결합니다.
i >> 1 연산은 i를 2로 나누는 것과 동일하고,
i & 1 연산은 i가 홀수인지 판별하여 1을 더하는 역할을 합니다.
*/
class Solution1_2 {
    public int[] countBits(int n) {
          // 결과를 저장할 배열 생성
        int[] ans = new int[n + 1];
        
        // 0부터 n까지 반복하면서 각 숫자의 1의 개수를 센다
        for (int i = 1; i <= n; i++) {
            // 짝수일 경우: ans[i] = ans[i / 2]
            // 홀수일 경우: ans[i] = ans[i - 1] + 1
            ans[i] = ans[i >> 1] + (i & 1);
        }
        
        return ans;
    }
}

/*
countBits 메서드는 입력으로 정수 n을 받습니다.
결과를 저장할 정수 배열 ans를 생성합니다. 이 배열의 길이는 n + 1입니다.
0부터 n까지 반복하면서 각 숫자의 이진수에서 1의 개수를 계산합니다.
현재 숫자 i가 짝수이면, ans[i]는 ans[i / 2]와 같습니다. i >> 1은 i를 오른쪽으로 한 비트 쉬프트한 것과 같고, 이는 i를 2로 나눈 것과 같습니다.
현재 숫자 i가 홀수이면, ans[i]는 ans[i - 1] + 1과 같습니다. i & 1은 i의 마지막 비트가 1인 경우 1을 반환하고, 0인 경우 0을 반환합니다.
반복이 끝난 후, 완성된 배열 ans를 반환합니다.
*/


/*
동적 계획법(Dynamic Programming, DP)은 
복잡한 문제를 해결하기 위해 문제를 더 작은 하위 문제로 나누고, 
각 하위 문제의 결과를 저장하여 중복 계산을 피하는 알고리즘 기법

메모이제이션(Memoization): 탑다운(Top-Down) 접근 방식으로, 
재귀적으로 문제를 풀되 이미 계산한 결과를 저장하여 재사용하는 방법입니다. 
필요한 하위 문제의 결과를 계산할 때마다 이를 메모리에 저장하고, 
같은 문제가 다시 나타나면 저장된 결과를 반환합니다.

타뷸레이션(Tabulation): 바텀업(Bottom-Up) 접근 방식으로, 
작은 하위 문제부터 차례대로 해결하여 최종 문제의 답을 구하는 방법입니다. 
결과를 저장할 테이블을 미리 준비하고, 하위 문제를 해결하면서 테이블을 채워나갑니다.

중복 부분 문제(Overlapping Subproblems): 동일한 작은 문제들이 여러 번 반복하여 나타나므로, 이를 한 번만 계산하고 저장해두면 효율적으로 문제를 해결할 수 있습니다.
최적 부분 구조(Optimal Substructure): 문제의 최적해가 하위 문제들의 최적해로 구성될 수 있어야 합니다.
*/


/*
https://leetcode.com/problems/pascals-triangle/description/

118. Pascal's Triangle

Pascal의 삼각형(Pascal's Triangle)은 매우 유명한 수학적 구조입니다.
각 행(row)은 이전 행의 값을 이용해 계산됩니다.
예를 들어, 세 번째 행의 두 번째 값은
두 번째 행의 첫 번째 값과 두 번째 값을 더한 값입니다.

Step-by-Step 설명
기본 아이디어:

각 행은 1로 시작하고 1로 끝납니다.
중간 값들은 바로 위 행의 두 값의 합입니다.
예시:

첫 번째 행: [1]
두 번째 행: [1, 1]
세 번째 행: [1, 2, 1]
네 번째 행: [1, 3, 3, 1]
다섯 번째 행: [1, 4, 6, 4, 1]

동적 계획법(DP):

각 행을 계산할 때 이전 행을 이용해 새로운 행을 계산합니다.
*/


class Solution2 {
    public List<List<Integer>> generate(int numRows) {
           // 결과를 저장할 리스트 초기화
        List<List<Integer>> triangle = new ArrayList<>();

        // 첫 번째 행 추가
        if (numRows >= 1) {
            List<Integer> firstRow = new ArrayList<>();
            firstRow.add(1);
            triangle.add(firstRow);
        }

        // 각 행을 순차적으로 계산
        for (int i = 1; i < numRows; i++) {
            List<Integer> prevRow = triangle.get(i - 1);
            List<Integer> currentRow = new ArrayList<>();

            // 첫 번째 값은 항상 1
            currentRow.add(1);

            // 중간 값들은 이전 행의 두 값을 더한 값
            for (int j = 1; j < i; j++) {
                int value = prevRow.get(j - 1) + prevRow.get(j);
                currentRow.add(value);
            }

            // 마지막 값도 항상 1
            currentRow.add(1);

            // 현재 행을 결과 리스트에 추가
            triangle.add(currentRow);
        }

        return triangle;
    }
}

/*
리스트 초기화: triangle 리스트는 각 행(row)을 저장합니다.
첫 번째 행 추가: 첫 번째 행은 항상 [1]입니다.

각 행 계산:
prevRow는 이전 행을 나타냅니다.
currentRow는 현재 행을 저장합니다.
첫 번째와 마지막 값은 항상 1입니다.
중간 값들은 prevRow의 값을 이용해 계산됩니다.

이 코드의 핵심은 동적 계획법을 이용해 이전 행의 값을 재사용하여
새로운 행을 계산하는 것입니다. 
이를 통해 효율적으로 Pascal의 삼각형을 생성할 수 있습니다.
*/


/*
https://leetcode.com/problems/fibonacci-number/description/

509. Fibonacci Number
*/

// 배열 사용
class Solution3_1 {
    public int fib(int n) {
           if (n <= 1) {
            return n;
        }
        
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        
        return dp[n];
    }
}

// 변수 사용
class Solution3_2 {
    public int fib(int n) {
       if (n <= 1) {
            return n;
        }
        
        int prev1 = 0;
        int prev2 = 1;
        int result = 0;
        
        for (int i = 2; i <= n; i++) {
            result = prev1 + prev2;
            prev1 = prev2;
            prev2 = result;
        }
        
        return result;
    }
}


/*
https://leetcode.com/problems/divisor-game/description/
1025. Divisor Game
*/
class Solution {
    public boolean divisorGame(int n) {
        boolean[] dp = new boolean[n + 1];
        
        // 기본값 설정
        dp[1] = false; // n = 1인 경우 Alice는 이길 수 없음
        
        for (int i = 2; i <= n; i++) {
            for (int x = 1; x < i; x++) {
                if (i % x == 0 && !dp[i - x]) {
                    dp[i] = true;
                    break; // 한 번 true가 되면 더 이상 확인할 필요 없음
                }
            }
        }
        
        return dp[n];
    }
}

/*
Alice와 Bob이 번갈아 가면서 게임을 합니다. 
초기에는 숫자 n이 주어지고, 각 플레이어는 다음 조건을 만족하는 x를 선택해야 합니다:

0 < x < n
n % x == 0 (즉, n을 x로 나눈 나머지가 0이어야 합니다)
그리고 선택한 x를 n에서 빼서 새로운 n으로 바꾸는 방식입니다.
더 이상 유효한 움직임을 할 수 없는 플레이어가 게임에서 지게 됩니다.
Alice가 먼저 시작합니다.

Alice가 이길 수 있는지 여부를 반환해야 합니다.

이 문제를 동적 계획법으로 해결하는 방법은 
각 n에 대해 Alice가 이길 수 있는지 여부를 저장하는 배열 dp를 사용하는 것입니다.


만약 n이 1이면, Alice는 이길 수 없습니다. (dp[1] = false)
2 이상의 숫자 n에 대해서, Alice는 n을 이길 수 있는지 결정하려면
n에서 가능한 모든 x (0 < x < n, n % x == 0)를 고려해야 합니다. 
Alice는 n - x로 이동하고, 이 이동이 Bob에게 지는 상황이 되는 경우 dp[n] = true가 됩니다.
즉, dp[n]는 n보다 작은 x 중에서 
n % x == 0인 모든 x에 대해 dp[n - x]가 false인 경우 true입니다.


boolean[] dp = new boolean[n + 1]; : 

n까지의 모든 수에 대해 Alice가 이길 수 있는지 여부를 저장하는 배열을 만듭니다.
dp[1] = false; : 기본적으로 n이 1인 경우 Alice는 이길 수 없습니다.

for (int i = 2; i <= n; i++) : 

2부터 n까지 반복하며 각 i에 대해 Alice가 이길 수 있는지 계산합니다.
for (int x = 1; x < i; x++) : 각 i에 대해 가능한 x를 모두 확인합니다.

if (i % x == 0 && !dp[i - x]) : 
i가 x로 나누어 떨어지고 dp[i - x]가 false인 경우, Alice는 이길 수 있습니다.

dp[i] = true; : Alice가 이길 수 있음을 표시합니다.

break; : 이미 true가 되었으므로 더 이상 확인할 필요가 없습니다.

return dp[n]; : 최종 결과를 반환합니다.

*/
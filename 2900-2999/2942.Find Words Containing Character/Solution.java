//First Solution:::

class Solution {
  public List<Integer> findWordsContaining(String[] words, char x) {
    List<Integer> result = new ArrayList<>();
    for (int i = 0; i < words.length; i++) {
      char[] chars = words[i].toCharArray();
      for (char c : chars) {
        if (c == x) {
          result.add(i);
          break;
        }
      }

    }
    return result;
  }
}

/*
 * 
 * 2nd Solution:::
 * class Solution{
 * public List<Integer>findWordsContaining(String[]words,char x){
 * List<Integer>result=new ArrayList<>();
 * for(int i=0;i<words.length;i++){
 * if(words[i].indexOf(x)!=-1){
 * result.add(i);
 * }
 * }
 * return result;
 * }
 * }
 */
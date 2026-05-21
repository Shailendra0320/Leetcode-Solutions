class Node
{
public:
  Node *children[10];
  bool isEnd;

  Node() cd../..
  {

    isEnd = false;

    for (int i = 0; i < 10; i++)
    {

      children[i] = NULL;
    }
  }

  bool containsKey(char ch)
  {

    return children[ch - '0'] != NULL;
  }

  Node *getNode(char ch)
  {

    return children[ch - '0'];
  }

  void putNode(char ch, Node *node)
  {

    children[ch - '0'] = node;
  }

  void setEnd()
  {

    isEnd = true;
  }
};

class Solution
{

  Node *root;

public:
  Solution()
  {

    root = new Node();
  }

  void insert(string word)
  {

    Node *node = root;

    for (int i = 0; i < word.length(); i++)
    {

      char ch = word[i];

      if (!node->containsKey(ch))
      {

        node->putNode(ch, new Node());
      }

      node = node->getNode(ch);
    }

    node->setEnd();
  }

  int findCommonPrefixLength(string word)
  {

    Node *node = root;

    int count = 0;

    for (int i = 0; i < word.length(); i++)
    {

      char ch = word[i];

      if (node->containsKey(ch))
      {

        node = node->getNode(ch);

        count++;
      }
      else
      {

        break;
      }
    }

    return count;
  }

  int longestCommonPrefix(vector<int> &arr1, vector<int> &arr2)
  {

    int maxLength = 0;

    for (int num : arr2)
    {

      insert(to_string(num));
    }

    for (int num : arr1)
    {

      int length = findCommonPrefixLength(to_string(num));

      maxLength = max(maxLength, length);
    }

    return maxLength;
  }
};
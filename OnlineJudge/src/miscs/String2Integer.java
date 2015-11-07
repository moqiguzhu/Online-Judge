package miscs;

public class String2Integer {
  public int atoi(String str) {
    // flag = 1说明是以"+"开头
    // flag = 2说明以“-”开头
    // flag = 0没有加减号
    int flag = 0;

    // 首先去掉两端的空格
    str = str.trim();

    // 判断是否合法
    String regex;
    regex = "[+-]?[0-9]+.*";
    if (!str.matches(regex)) {
      return 0;
    }
    if (str.startsWith("+")) {
      flag = 1;
      str = str.substring(1, str.length());
    } else if (str.startsWith("-")) {
      flag = 2;
      str = str.substring(1, str.length());
    } else {
      // do nothing
    }
    regex = "[^0-9]";
    String result;
    result = str.split(regex)[0];

    // 判断是否能转换
    if (result == null || result.equals("")) {
      return 0;
    }

    // 判断是否越界
    try {
      if (flag == 2) {
        return -Integer.valueOf(result);
      } else {
        return Integer.valueOf(result);
      }
    } catch (Exception e) {
      if (flag == 2) {
        return Integer.MIN_VALUE;
      } else {
        return Integer.MAX_VALUE;
      }
    }
  }

  public int testParseInt(String str) {
    int result = Integer.parseInt(str);
    return result;
  }

  public static void main(String[] args) {
    String2Integer atoi = new String2Integer();
    String test = "-2147483648";
    
    System.out.println(atoi.atoi(test));
    System.out.println(Integer.MAX_VALUE / 10);
  }
}

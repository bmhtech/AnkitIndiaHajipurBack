package com.AnkitIndia.Utility;

public class NumToWord
{
    final static String [] a1={"Hundred","Thousand","Lakh","Crore"};
    final static String [] a2={"Ten","Twenty","Thirty","Forty","Fifty","Sixty","Seventy","Eighty","Ninty"};
    final static String [] a3={"Eleven","Twelve","Thirteen","Forteen","Fifteen","Sixteen","Seventeen","Eighteen","Ninteen"};
    final static String [] a4={"Zero","One","Two","Three","Four","Five","Six","Seven","Eight","Nine"};
    final static String [] a5={"Cent"};
    static String  word="";
    
    private static String oneNo(int n1)
    {
           return (a4[n1]+" ");

    }
    private static String twoNo(int n1, int n2)
    {
           if(n1==0&&n2==0)
           {
                return("");
           }
           if(n1==0)
           {
                return(oneNo(n2));
           }
           if (n1==1 && n2==0)
           {
                return(a2[0]+" ");
           }
           else
           {
                if (n1>1)
                {
                   if(n2>0)
                   {
                        return(a2[n1-1]+" "+a4[n2]+" ");
                   }
                   else
                   {
                        return(a2[n1-1]+" ");
                   }
                }
                else
                {
                    return(a3[n2-1]+" ");
                }
            }
    }

    public static String numberConvert(int no)
    {
        String value=""+no;
        if(value.length()==1)
        {
            word=oneNo(Integer.parseInt(""+value.charAt(0)));
        }
        if(value.length()==2)
        {
            int n1=Integer.parseInt(""+value.charAt(0));
            int n2=Integer.parseInt(""+value.charAt(1));
            word=twoNo(n1,n2);
        }
        if(value.length()==3)
        {
            int n1=Integer.parseInt(""+value.charAt(0));
            int n2=Integer.parseInt(""+value.charAt(1));
            int n3=Integer.parseInt(""+value.charAt(2));
            word=oneNo(n1)+a1[0]+" ";
            word=word+twoNo(n2,n3);
        }
        if(value.length()==4)
        {
            int n1=Integer.parseInt(""+value.charAt(0));
            int n2=Integer.parseInt(""+value.charAt(1));
            int n3=Integer.parseInt(""+value.charAt(2));
            int n4=Integer.parseInt(""+value.charAt(3));
            word=oneNo(n1)+a1[1]+" ";
            if(oneNo(n2).equals("Zero "))
                word=word;
            else
                word=word+oneNo(n2)+a1[0]+" ";
            word=word+twoNo(n3,n4);
        }
        if(value.length()==5)
        {
            int n1=Integer.parseInt(""+value.charAt(0));
            int n2=Integer.parseInt(""+value.charAt(1));
            int n3=Integer.parseInt(""+value.charAt(2));
            int n4=Integer.parseInt(""+value.charAt(3));
            int n5=Integer.parseInt(""+value.charAt(4));
            word=twoNo(n1,n2)+a1[1]+" ";
            if(oneNo(n3).equals("Zero "))
                word=word;
            else
                word=word+oneNo(n3)+a1[0]+" ";
            word=word+twoNo(n4,n5);
        }
        if(value.length()==6)
        {
            int n1=Integer.parseInt(""+value.charAt(0));
            int n2=Integer.parseInt(""+value.charAt(1));
            int n3=Integer.parseInt(""+value.charAt(2));
            int n4=Integer.parseInt(""+value.charAt(3));
            int n5=Integer.parseInt(""+value.charAt(4));
            int n6=Integer.parseInt(""+value.charAt(5));
            word=oneNo(n1)+a1[2]+" ";
            if(twoNo(n2,n3).equals(""))
                word=word;
            else
                word=word+twoNo(n2,n3)+a1[1]+" ";
            if(oneNo(n4).equals("Zero "))
                word=word;
            else
                word=word+oneNo(n4)+a1[0]+" ";
            word=word+twoNo(n5,n6);
        }
        if(value.length()==7)
        {
            int n1=Integer.parseInt(""+value.charAt(0));
            int n2=Integer.parseInt(""+value.charAt(1));
            int n3=Integer.parseInt(""+value.charAt(2));
            int n4=Integer.parseInt(""+value.charAt(3));
            int n5=Integer.parseInt(""+value.charAt(4));
            int n6=Integer.parseInt(""+value.charAt(5));
            int n7=Integer.parseInt(""+value.charAt(6));
            word=twoNo(n1,n2)+a1[2]+" ";
            if(twoNo(n3,n4).equals(""))
                word=word;
            else
                word=word+twoNo(n3,n4)+a1[1]+" ";
            if(oneNo(n5).equals("Zero "))
                word=word;
            else
                word=word+oneNo(n5)+a1[0]+" ";
            word=word+twoNo(n6,n7);
        }
        if(value.length()==8)
        {
            int n1=Integer.parseInt(""+value.charAt(0));
            int n2=Integer.parseInt(""+value.charAt(1));
            int n3=Integer.parseInt(""+value.charAt(2));
            int n4=Integer.parseInt(""+value.charAt(3));
            int n5=Integer.parseInt(""+value.charAt(4));
            int n6=Integer.parseInt(""+value.charAt(5));
            int n7=Integer.parseInt(""+value.charAt(6));
            int n8=Integer.parseInt(""+value.charAt(7));
            word=oneNo(n1)+a1[3]+" ";
            if(twoNo(n2,n3).equals(""))
                word=word;
            else
                word=word+twoNo(n2,n3)+a1[2]+" ";
            if(twoNo(n4,n5).equals(""))
                word=word;
            else
                word=word+twoNo(n4,n5)+a1[1]+" ";
            if(oneNo(n6).equals("Zero "))
                word=word;
            else
                word=word+oneNo(n6)+a1[0]+" ";
            word=word+twoNo(n7,n8);
        }

        if(value.length()==9)
        {
            int n1=Integer.parseInt(""+value.charAt(0));
            int n2=Integer.parseInt(""+value.charAt(1));
            int n3=Integer.parseInt(""+value.charAt(2));
            int n4=Integer.parseInt(""+value.charAt(3));
            int n5=Integer.parseInt(""+value.charAt(4));
            int n6=Integer.parseInt(""+value.charAt(5));
            int n7=Integer.parseInt(""+value.charAt(6));
            int n8=Integer.parseInt(""+value.charAt(7));
            int n9=Integer.parseInt(""+value.charAt(8));
            word=twoNo(n1,n2)+a1[3]+" ";
            if(twoNo(n3,n4).equals(""))
                word=word;
            else
                word=word+twoNo(n3,n4)+a1[2]+" ";
            if(twoNo(n5,n6).equals(""))
                word=word;
            else
                word=word+twoNo(n5,n6)+a1[1]+" ";
            if(oneNo(n6).equals("Zero "))
                word=word;
            else
                word=word+oneNo(n7)+a1[0]+" ";
            word=word+twoNo(n8,n8);
        }
        return(word);
    }

    private static String oneNoZero(int no1)
    {
           return (a5[no1]+" ");
    }
    
    public static String wordConvert(Double no)
    {
        String value=""+no;
        if(value.length()==1)
        {
            word=oneNo(Integer.parseInt(""+value.charAt(0)));
        }
        if(value.length()==2)
        {
            int n1=Integer.parseInt(""+value.charAt(0));
            int n2=Integer.parseInt(""+value.charAt(1));
            word=twoNo(n1,n2);
        }
        if(value.length()==3)
        {
            int n1=Integer.parseInt(""+value.charAt(0));
            int n2=Integer.parseInt(""+value.charAt(1));
            int n3=Integer.parseInt(""+value.charAt(2));
            word=oneNo(n1)+a1[0]+" ";
            word=word+twoNo(n2,n3);
        }
        if(value.length()==4)
        {
            int n1=Integer.parseInt(""+value.charAt(0));
            int n2=Integer.parseInt(""+value.charAt(1));
            int n3=Integer.parseInt(""+value.charAt(2));
            int n4=Integer.parseInt(""+value.charAt(3));
            word=oneNo(n1)+a1[1]+" ";
            if(oneNo(n2).equals("Zero "))
                word=word;
            else
                word=word+oneNo(n2)+a1[0]+" ";
            word=word+twoNo(n3,n4);
        }
        if(value.length()==5)
        {
            int n1=Integer.parseInt(""+value.charAt(0));
            int n2=Integer.parseInt(""+value.charAt(1));
            int n3=Integer.parseInt(""+value.charAt(2));
            int n4=Integer.parseInt(""+value.charAt(3));
            int n5=Integer.parseInt(""+value.charAt(4));
            word=twoNo(n1,n2)+a1[1]+" ";
            if(oneNo(n3).equals("Zero "))
                word=word;
            else
                word=word+oneNo(n3)+a1[0]+" ";
            word=word+twoNo(n4,n5);
        }
        if(value.length()==6)
        {
            int n1=Integer.parseInt(""+value.charAt(0));
            int n2=Integer.parseInt(""+value.charAt(1));
            int n3=Integer.parseInt(""+value.charAt(2));
            int n4=Integer.parseInt(""+value.charAt(3));
            int n5=Integer.parseInt(""+value.charAt(4));
            int n6=Integer.parseInt(""+value.charAt(5));
            word=oneNo(n1)+a1[2]+" ";
            if(twoNo(n2,n3).equals(""))
                word=word;
            else
                word=word+twoNo(n2,n3)+a1[1]+" ";
            if(oneNo(n4).equals("Zero "))
                word=word;
            else
                word=word+oneNo(n4)+a1[0]+" ";
            word=word+twoNo(n5,n6);
        }
        if(value.length()==7)
        {
            int n1=Integer.parseInt(""+value.charAt(0));
            int n2=Integer.parseInt(""+value.charAt(1));
            int n3=Integer.parseInt(""+value.charAt(2));
            int n4=Integer.parseInt(""+value.charAt(3));
            int n5=Integer.parseInt(""+value.charAt(4));
            int n6=Integer.parseInt(""+value.charAt(5));
            int n7=Integer.parseInt(""+value.charAt(6));
            word=twoNo(n1,n2)+a1[2]+" ";
            if(twoNo(n3,n4).equals(""))
                word=word;
            else
                word=word+twoNo(n3,n4)+a1[1]+" ";
            if(oneNo(n5).equals("Zero "))
                word=word;
            else
                word=word+oneNo(n5)+a1[0]+" ";
            word=word+twoNo(n6,n7);
        }
        if(value.length()==8)
        {
            int n1=Integer.parseInt(""+value.charAt(0));
            int n2=Integer.parseInt(""+value.charAt(1));
            int n3=Integer.parseInt(""+value.charAt(2));
            int n4=Integer.parseInt(""+value.charAt(3));
            int n5=Integer.parseInt(""+value.charAt(4));
            int n6=Integer.parseInt(""+value.charAt(5));
            int n7=Integer.parseInt(""+value.charAt(6));
            int n8=Integer.parseInt(""+value.charAt(7));
            word=oneNo(n1)+a1[3]+" ";
            if(twoNo(n2,n3).equals(""))
                word=word;
            else
                word=word+twoNo(n2,n3)+a1[2]+" ";
            if(twoNo(n4,n5).equals(""))
                word=word;
            else
                word=word+twoNo(n4,n5)+a1[1]+" ";
            if(oneNo(n6).equals("Zero "))
                word=word;
            else
                word=word+oneNo(n6)+a1[0]+" ";
            word=word+twoNo(n7,n8);
        }

        if(value.length()==9)
        {
            int n1=Integer.parseInt(""+value.charAt(0));
            int n2=Integer.parseInt(""+value.charAt(1));
            int n3=Integer.parseInt(""+value.charAt(2));
            int n4=Integer.parseInt(""+value.charAt(3));
            int n5=Integer.parseInt(""+value.charAt(4));
            int n6=Integer.parseInt(""+value.charAt(5));
            int n7=Integer.parseInt(""+value.charAt(6));
            int n8=Integer.parseInt(""+value.charAt(7));
            int n9=Integer.parseInt(""+value.charAt(8));
            word=twoNo(n1,n2)+a1[3]+" ";
            if(twoNo(n3,n4).equals(""))
                word=word;
            else
                word=word+twoNo(n3,n4)+a1[2]+" ";
            if(twoNo(n5,n6).equals(""))
                word=word;
            else
                word=word+twoNo(n5,n6)+a1[1]+" ";
            if(oneNo(n6).equals("Zero "))
                word=word;
            else
                word=word+oneNo(n7)+a1[0]+" ";
            word=word+twoNo(n8,n8);
        }
        return(word);
    }
    
}

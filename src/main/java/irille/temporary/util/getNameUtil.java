package irille.temporary.util;

public class getNameUtil {
	 private static String firstName="赵钱孙李周吴郑王冯陈褚卫蒋沈韩杨朱秦尤许何吕施张孔曹严华金魏陶姜戚谢邹喻窦章云苏潘葛奚范彭郎鲁韦昌马苗凤花方俞任袁柳鲍史唐费薛雷贺倪汤滕殷罗毕郝邬傅皮宋茅庞熊纪舒屈项祝董梁杜阮闵席季贾路颜郭林刁钟徐邱高田胡凌霍虞万支管卢莫裘缪邓单郭岳梁";
	 private static String girl="秀娟英华慧巧美娜静淑惠珠翠雅芝玉萍红娥玲芬芳燕彩春菊兰凤洁梅琳素云莲真环雪荣爱妹霞香月莺媛艳瑞凡佳嘉琼勤珍贞莉桂娣叶璧璐娅琦晶妍茜秋珊莎锦黛青倩婷姣婉娴瑾颖露瑶怡婵雁蓓纨仪荷丹蓉眉君琴蕊薇菁梦岚苑婕馨瑗琰韵融园艺咏卿聪澜纯毓悦昭冰爽琬茗羽希宁欣飘育滢馥筠柔竹霭凝晓欢霄枫芸菲寒伊亚宜可姬舒影荔枝思丽 ";  
	 private static String boy="伟刚勇毅俊峰强军平保东文辉力明永健世广志义兴良海山仁波宁贵福生龙元全国胜学祥才发武新利清飞彬富顺信子杰涛昌成康星光天达安岩中茂进林有坚和彪博诚先敬震振壮会思群豪心邦承乐绍功松善厚庆磊民友裕河哲江超浩亮政谦亨奇固之轮翰朗伯宏言若鸣朋斌梁栋维启克伦翔旭鹏泽晨辰士以建家致树炎德行时泰盛雄琛钧冠策腾楠榕风航弘"; 
	 public static final String[] FEMALE_FIRST_NAMES ={"Mary", "Patricia", "Linda", "Barbara", "Elizabeth", "Jennifer", "Maria", "Susan", "Margaret", "Dorothy", "Lisa", "Nancy", "Karen", "Betty", "Helen", "Sandra", "Donna","Carol", "Ruth", "Sharon", "Michelle", "Laura", "Sarah", "Kimberly", "Deborah", "Jessica","Shirley", "Cynthia", "Angela", "Melissa", "Brenda", "Amy", "Anna", "Rebecca", "Virginia","Kathleen", "Pamela", "Martha", "Debra", "Amanda", "Stephanie", "Carolyn", "Christine","Marie", "Janet", "Catherine", "Frances", "Ann", "Joyce", "Diane", "Alice", "Julie","Heather", "Teresa", "Doris", "Gloria", "Evelyn", "Jean", "Cheryl", "Mildred", "Katherine","Joan", "Ashley", "Judith", "Rose", "Janice", "Kelly", "Nicole", "Judy", "Christina","Kathy", "Theresa", "Beverly", "Denise", "Tammy", "Irene", "Jane", "Lori", "Rachel","Marilyn", "Andrea", "Kathryn", "Louise", "Sara", "Anne", "Jacqueline", "Wanda", "Bonnie","Julia", "Ruby", "Lois", "Tina", "Phyllis", "Norma", "Paula", "Diana", "Annie", "Lillian","Emily", "Robin", "Peggy", "Crystal", "Gladys", "Rita", "Dawn", "Connie", "Florence","Tracy", "Edna", "Tiffany", "Carmen", "Rosa", "Cindy", "Grace", "Wendy", "Victoria", "Edith","Kim", "Sherry", "Sylvia", "Josephine", "Thelma", "Shannon", "Sheila", "Ethel", "Ellen","Elaine", "Marjorie", "Carrie", "Charlotte", "Monica", "Esther", "Pauline", "Emma","Juanita", "Anita", "Rhonda", "Hazel", "Amber", "Eva", "Debbie", "April", "Leslie", "Clara","Lucille", "Jamie", "Joanne", "Eleanor", "Valerie", "Danielle", "Megan", "Alicia", "Suzanne","Michele", "Gail", "Bertha", "Darlene", "Veronica", "Jill", "Erin", "Geraldine", "Lauren","Cathy", "Joann", "Lorraine", "Lynn", "Sally", "Regina", "Erica", "Beatrice", "Dolores","Bernice", "Audrey", "Yvonne", "Annette", "June", "Samantha", "Marion", "Dana", "Stacy", "Ana", "Renee", "Ida", "Vivian", "Roberta", "Holly", "Brittany", "Melanie", "Loretta","Yolanda", "Jeanette", "Laurie", "Katie", "Kristen", "Vanessa", "Alma", "Sue", "Elsie", "Beth", "Jeanne"};
	 private static final String[] MALE_FIRST_NAMES = {"James", "John", "Robert", "Michael", "William", "David", "Richard", "Charles", "Joseph","Thomas", "Christopher", "Daniel", "Paul", "Mark", "Donald", "George", "Kenneth", "Steven","Edward", "Brian", "Ronald", "Anthony", "Kevin", "Jason", "Matthew", "Gary", "Timothy","Jose", "Larry", "Jeffrey", "Frank", "Scott", "Eric", "Stephen", "Andrew", "Raymond","Gregory", "Joshua", "Jerry", "Dennis", "Walter", "Patrick", "Peter", "Harold", "Douglas","Henry", "Carl", "Arthur", "Ryan", "Roger", "Joe", "Juan", "Jack", "Albert", "Jonathan","Justin", "Terry", "Gerald", "Keith", "Samuel", "Willie", "Ralph", "Lawrence", "Nicholas","Roy", "Benjamin", "Bruce", "Brandon", "Adam", "Harry", "Fred", "Wayne", "Billy", "Steve","Louis", "Jeremy", "Aaron", "Randy", "Howard", "Eugene", "Carlos", "Russell", "Bobby","Victor", "Martin", "Ernest", "Phillip", "Todd", "Jesse", "Craig", "Alan", "Shawn","Clarence", "Sean", "Philip", "Chris", "Johnny", "Earl", "Jimmy", "Antonio", "Danny","Bryan", "Tony", "Luis", "Mike", "Stanley", "Leonard", "Nathan", "Dale", "Manuel", "Rodney","Curtis", "Norman", "Allen", "Marvin", "Vincent", "Glenn", "Jeffery", "Travis", "Jeff","Chad", "Jacob", "Lee", "Melvin", "Alfred", "Kyle", "Francis", "Bradley", "Jesus", "Herbert","Frederick", "Ray", "Joel", "Edwin", "Don", "Eddie", "Ricky", "Troy", "Randall", "Barry","Alexander", "Bernard", "Mario", "Leroy", "Francisco", "Marcus", "Micheal", "Theodore","Clifford", "Miguel", "Oscar", "Jay", "Jim", "Tom", "Calvin", "Alex", "Jon", "Ronnie","Bill", "Lloyd", "Tommy", "Leon", "Derek", "Warren", "Darrell", "Jerome", "Floyd", "Leo","Alvin", "Tim", "Wesley", "Gordon", "Dean", "Greg", "Jorge", "Dustin", "Pedro", "Derrick","Dan", "Lewis", "Zachary", "Corey", "Herman", "Maurice", "Vernon", "Roberto", "Clyde","Glen", "Hector", "Shane", "Ricardo", "Sam", "Rick", "Lester", "Brent", "Ramon", "Charlie","Tyler", "Gilbert", "Gene"};
	 private static String [] consult_title={"Romania Wholesale Shoes","Juna ladies dress shoes","FOLLOW ME girls","Women's Chic Pointed","Aokang official men's","British Style Simple Ankle Shoes","Men's casual velcro soft sandals","Chunky heel single shoes","Men's Leather Shoes Wear-resistant","Men's Leather Shoes Black Shoes","Men's Leather Shoes Lace-up ","Men's Leather Shoes Wear-resistant","Men's Simple Sneakers Lace-up","Men's loafers Slip-ons Simple","Women's Snow Boots Warm","Unisex Snow Boots Warm Chic","Boy's and Grl's Snow Boots","Men's Snow Boots Water-proof","Men's Snow Boots Water-proof","Men's Snow Boots Waterproof Lambs","Girl's Doll Bowknot Round","Girl's Pink Doll Round Head","Girl's Pink Doll Round Head","Girl's PU Velcro Flat Sandals","Girl's PU Velcro Flat Sandals","Girl's Pink PU Velcro Strap","Girl's Pink PU Velcro Strap","Girl's PU Sequins Sandals","Boy's Oxford Cloth Shoes Spring","Boy's Microfiber Single Shoes Spring ","Girl's Canvas Sandals Leather Fashionable Bowknot ","Girl's T-strap Sandals Leather Flower","Girl's Glitter Straps Sandals Leather Metal","Boy's Camel Ankle Shoes","Girl's Pink Leisure Shoes Fashionable","Lady's Ankle Flat Shoes Simple","Women's Simple Sandals","Women's Pointed Single Shoes","Women's Pointed Single Shoes with","Women's Snake PU Ankle","Women's New Shic Buckle Side-zipper Vintage Casual Ankle Boots for Autumn and Winter","Women's New Chic Riding Boots","Women's New Chic Riding Boots","Women's New Chic Riding Boots","Women's Chic Casual Vintage Tassel","Men's Spring and Autumn","Women's Casual Canvas Shoes ","Men's loafers Slip-ons Casual Shoes","Men's White Casual Shoes Fashionable","Men's Slip-ons PU Shoes Lacing up","Men's PU Ankle Shoes Lacing up","Men's Jersey Single Shoes Lacing up","Men's Velvet Oxford Shoes Fashionable","Men's White Casual Shoes Lacing up","Men's Slip-ons Casual Shoes Simple","Men's Casual Ankle Boots Fashion","Women's Canvas Shoes Fashion","Women's Canvas Shoes Fashion","Women's Leather Lycra Tall Boots","Women's Back-zipper Boots","Women's Back-zipper Boots","Women's PU Boots British","Women's Screw Thread ","Ladies' Leather Tall Boots Fashionable ","Ladies' Leather Simple Tall Boots Fashionable","Ladies' PU Leather Middle Boots","Ladies' Shining Glitter Sneaker","Women's Microfiber Middle Boots","Girl's Flower PU Sandals Lovely Velcro","Women's Chic Pointed Toe","Women's Chic Western Style ","Women's Chic Western Style ","Women's Microfiber Middle ","Women's Tan Microfiber PU","Women's PU Ankle Boots with","Women's Shoes Net Surface","Men's Casual Shallow Single Shoes","Men's Chic Shallow Single","Men's PU Casual Shoes Black ","Men's Nude Canvas Shoes Fashionable","Women's Hollow Loafer Fashionable","Women's Hollow Loafer Fashionable","Girls' Flower Cotton Shoes Fashionable ","Women's Simple Loafer Slip-ons","Women's Fashionable Sequins Single","Women's Fashionable Metal Buckle","Women's Ankle Boots Simple Metal","Women's Casual Cross-strap","Women's Fashion Color-matching ","Women's High Heel Shoes Fashionable","Men's Simple Business Shoes","Dabowen Ladies Fashion Color-matching","abowen Women's White Shoes","abowen Women's White Shoes","Boy and Girl's Fashionable Sneakers","Women's Fashionable Shoes Spring","Women's Pointed Short Boots Spring","Women's Peep Toe Shoes"};  
	 private static String [] city_name={"北京市","天津市","沈阳市","长春市","上海市","杭州市","温州市","丽水市","嘉兴市","成都市","郑州市","丹东市 ","福州市 ","厦门市 ","莆田市 ","福鼎市 ","武夷山市 ","临川市","景德镇市 ","南昌市 ","鹰潭市 ","赣州市","泉州市 ","义乌市 ","萧山市 ","舟山市 ","绍兴市 ","金华市 ","湖州市 ","衢州市 ","合肥市","阜阳市 ","马鞍山市","芜湖市","九江市","济南市 ","青岛市 ","淄博市 ","潍坊市 ","开封市","洛阳市 ","郑州市","许昌市 ","黄冈市","咸宁市","孝感市","武汉市","黄石市","宜昌市","长沙市 ","株洲市 ","常德市 ","张家界市 ","衡阳市","东莞市 ","广州市 ","韶关市 ","珠海市 ","汕头市","三亚市 ","泸州市 ","自贡市","绵阳市 ","广元市 ","南充市","达州市","广安市 ","宜宾市","遂宁市 ","内江市 ","贵阳市 ","遵义市 ","昆明市 ","曲靖市 ","玉溪市 ","西安市 ","安康市 ","汉中市 ","宝鸡市 ","延安市 ","咸阳市 ","兰州市","无锡市 ","南京市","徐州市 ","连云港市 ","镇江市 ","扬州市 ","盐城市 ","苏州市","常州市"};
	 private static String name_sex = "";  
	
	 /**
	  * 随机获取中文名称
	  * @return
	  */
	 public static String getChineseName() {  
	        int index=getNum(0, firstName.length()-1);  
	        String first=firstName.substring(index, index+1);  
	        int sex=getNum(0,1);  
	        String str=boy;  
	        int length=boy.length();  
	        if(sex==0){  
	        str=girl;  
	        length=girl.length();  
	        name_sex="女";  
	        }else {  
	        name_sex="男";  
	        }  
	        index=getNum(0,length-1);  
	        String second=str.substring(index, index+1);  
	        int hasThird=getNum(0,1);  
	        String third="";  
	        if(hasThird==1){  
	            index=getNum(0,length-1);  
	            third=str.substring(index, index+1);  
	        }  
	        return first+second+third;  
	    }  
		/**
		 * 取随机数 
		 * @param start
		 * @param end
		 * @return
		 */
		public static int getNum(int start,int end) {  
		        return (int)(Math.random()*(end-start+1)+start);  
		 }  
		/**
		 * 随机获取英文名
		 * @return
		 */
		public static String getEnName(){
		int sex=getNum(0,1); 
		String enName="";
		if(sex==1){
		enName=FEMALE_FIRST_NAMES[getNum(0,FEMALE_FIRST_NAMES.length-1)];
		}else{
		enName=MALE_FIRST_NAMES[getNum(0,MALE_FIRST_NAMES.length-1)];	
		}
		return enName;	
		} 
		/**
		 * 获取询盘名称
		 * @return
		 */
		public static String getTitle(){
		String consultName=consult_title[getNum(0, consult_title.length-1)];	
		String consultTitl="";
		if (consultName.length()>50) {
		consultTitl=consultName.substring(0, 50);
		return consultTitl+"...";
		}else{
		consultTitl=consultName;	
		return consultTitl;
		}
		}
		/**
		 * 随机获取鞋乐购下单用户来自城市名称
		 * @return
		 */
	    public static String getCityName(){
	    String cityName=city_name[getNum(0, city_name.length-1)];
	    return cityName;	
	    }
	    /**
	     * 随机获取能量值
	     * @return
	     */
	    public static int  getPowerValue(){
	    int  num=getNum(0,200)*10;
	    return num;	
	    }
	    public static void main(String[] args) {
			for (int i = 0; i < 80; i++) {
				System.out.println(getNum(1,4));
			}
		}
}
	

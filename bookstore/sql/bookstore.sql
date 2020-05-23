/*
Navicat MySQL Data Transfer

Source Server         : data
Source Server Version : 50724
Source Host           : localhost:3306
Source Database       : bookstore1

Target Server Type    : MYSQL
Target Server Version : 50724
File Encoding         : 65001

Date: 2019-03-03 18:35:21
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `admin`
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `aid` char(50) NOT NULL,
  `adminname` varchar(200) NOT NULL,
  `adminpassword` varchar(150) NOT NULL,
  PRIMARY KEY (`aid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('1', 'liudehua', '123');

-- ----------------------------
-- Table structure for `book`
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book` (
  `bid` char(50) NOT NULL,
  `bname` varchar(50) NOT NULL,
  `price` decimal(5,1) NOT NULL,
  `author` char(30) NOT NULL,
  `image` varchar(200) NOT NULL,
  `evaluate` varchar(800) DEFAULT NULL,
  `cid` char(50) DEFAULT NULL,
  `state` int(10) NOT NULL,
  PRIMARY KEY (`bid`),
  KEY `cid` (`cid`),
  CONSTRAINT `book_ibfk_1` FOREIGN KEY (`cid`) REFERENCES `category` (`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES ('01', '克雷洛夫寓言全集', '12.3', '克雷洛夫', 'book_img/1-0.jpg', '					分为九卷和未收入九卷的寓言这十个部分，收录了乌鸦和狐狸、青蛙想要一个国王、承包商和鞋匠、四重奏、杰米扬的鱼汤、狼和牧人、老鼠开会、变老的狮子、牧人、羞愧的赌徒等两百余篇经典的克雷洛夫寓言，精彩不断！\r\n					', '1', '0');
INSERT INTO `book` VALUES ('02', '苹果树上的外婆', '36.1', '米拉·洛贝', 'book_img/1-2.jpg', '					《苹果树上的外婆》是2006年新蕾出版社出版的图书，作者是米拉·洛贝。主要讲述了安迪与两位祖母的故事。本书曾荣获1965年奥地利国家儿童与青少年文学奖，问世以来已经成为公认的德语儿童文学经典作品，直到现在仍在不断再版，它是米拉·洛贝众多儿童文学作品中，被翻译出版最多的一部，成为世界各国孩子们都爱读的故事。\r\n					', '1', '1');
INSERT INTO `book` VALUES ('03', '夏洛的网', '54.5', 'E·B·怀特', 'book_img/1-3.jpg', '《夏洛的网》是一部描写关于友情的童话，在朱克曼家的谷仓里，小猪威尔伯和蜘蛛夏洛建立了最真挚的友谊。威尔伯的生命有危险时，看似渺小的夏洛用自己的力量救了威尔伯，但，这时，蜘蛛夏洛的生命却走到的尽头……作者用童话的叙事风格表现出一分对生命本身的赞美与眷恋，给了我们关于生命的深沉的思索。', '1', '1');
INSERT INTO `book` VALUES ('04', '悲惨世界', '34.8', '维克多·雨果', 'book_img/2-1.jpg', '《悲惨世界》是由法国作家维克多·雨果在1862年发表的一部长篇小说，其内容涵盖了拿破仑战争和之后的十几年的时间。故事的主线围绕主人公土伦苦刑犯冉·阿让（Jean Valjean）的个人经历，融进了法国的历史、革命、战争、道德哲学、法律、正义、宗教信仰。该作多次被改编演绎成影视作品。', '2', '1');
INSERT INTO `book` VALUES ('05', '苏菲的世界', '75.6', '乔斯坦·贾德', 'book_img/2-2.jpg', '这是一本关于哲学史的小说。 20世纪百部经典著作之一。1994年获\"德国青少年文学奖\"与\"最优秀作品奖\"。《苏菲的世界》以小说的形式，通过一名哲学导师向一个叫苏菲的女孩传授哲学知识的经过，揭示了西方哲学史发展的历程。', '2', '1');
INSERT INTO `book` VALUES ('06', '天蓝色的彼岸', '56.2', '艾利克斯·希尔', 'book_img/2-3.jpg', '该小说讲述了因车祸死去的小男孩哈里，牵挂着爸爸、妈妈、姐姐和朋友，在幽灵阿瑟的帮助下，重返人间和他们做最后的告别，表达自己生前未来得及说出的爱的故事。该小说透过温暖清雅的笔调和具有童真的视角以及充满哲思的语言，唤起了人们内心深处的美好情感，启迪了人们对于生与死的深刻思索。', '2', '1');
INSERT INTO `book` VALUES ('07', '傲慢与偏见', '85.6', '简·奥斯汀', 'book_img/3-1.jpg', '小说描写了小乡绅班纳特五个待字闺中的千金，主角是二女儿伊丽莎白。她在舞会上认识了达西，但是耳闻他为人傲慢，一直对他心生排斥，经历一番周折，伊丽莎白解除了对达西的偏见，达西也放下傲慢，有情人终成眷属。', '3', '1');
INSERT INTO `book` VALUES ('08', '巴黎圣母院', '35.6', '维克多·雨果', 'book_img/3-2.jpg', '《巴黎圣母院》的文学价值以及社会意义，影响深远。这部小说，打破了以往古典主义的桎梏，是浪漫主义作品中一座里程碑。《巴黎圣母院》面世之后，曾多次改编为电影、动画片、戏剧等。', '3', '1');
INSERT INTO `book` VALUES ('09', '战争与和平', '46.9', '托尔斯泰', 'book_img/3-3.jpg', '该作以1812年的卫国战争为中心，反映从1805到1820年间的重大历史事件。以鲍尔康斯、别祖霍夫、罗斯托夫和库拉金四大贵族的经历为主线，在战争与和平的交替描写中把众多的事件和人物串联起来。', '3', '1');

-- ----------------------------
-- Table structure for `category`
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `cid` char(50) NOT NULL,
  `cname` varchar(50) NOT NULL,
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES ('1', '小学阶段');
INSERT INTO `category` VALUES ('2', '初中阶段');
INSERT INTO `category` VALUES ('3', '高中阶段');

-- ----------------------------
-- Table structure for `orderitem`
-- ----------------------------
DROP TABLE IF EXISTS `orderitem`;
CREATE TABLE `orderitem` (
  `iid` char(50) NOT NULL,
  `count` int(11) DEFAULT NULL,
  `subtotal` decimal(10,1) DEFAULT NULL,
  `oid` char(50) DEFAULT NULL,
  `bid` char(50) DEFAULT NULL,
  PRIMARY KEY (`iid`),
  KEY `oid` (`oid`),
  KEY `bid` (`bid`),
  CONSTRAINT `orderitem_ibfk_1` FOREIGN KEY (`oid`) REFERENCES `orders` (`oid`),
  CONSTRAINT `orderitem_ibfk_2` FOREIGN KEY (`bid`) REFERENCES `book` (`bid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orderitem
-- ----------------------------
INSERT INTO `orderitem` VALUES ('7BAFBA2DEA8348D1A0E2024CDD2039B8', '444', '15451.2', 'ADF7B04B82074C478A2B2C203D0CD287', '04');
INSERT INTO `orderitem` VALUES ('D1B15E82FD984DDCB649938641CBCD59', '1', '54.5', '9717FFE2EB1547D38819833F4A22BBC0', '03');
INSERT INTO `orderitem` VALUES ('F83B10794E3342C0B3D8DF72113655CC', '1', '75.6', '33B8A3F341134FD196A615D808F98EC7', '05');

-- ----------------------------
-- Table structure for `orders`
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `oid` char(50) NOT NULL,
  `ordertime` datetime DEFAULT NULL,
  `total` decimal(10,1) DEFAULT NULL,
  `state` smallint(1) DEFAULT NULL,
  `cellphone` varchar(20) DEFAULT NULL,
  `address` varchar(200) DEFAULT NULL,
  `uid` char(32) DEFAULT NULL,
  PRIMARY KEY (`oid`),
  KEY `uid` (`uid`),
  CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `tb_user` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES ('33B8A3F341134FD196A615D808F98EC7', '2019-02-21 19:58:17', '75.6', '4', '123456', '发烧反复', '02740169E6E542B3A400DFBCA5289589');
INSERT INTO `orders` VALUES ('9717FFE2EB1547D38819833F4A22BBC0', '2019-02-21 20:01:17', '54.5', '4', '', '', '02740169E6E542B3A400DFBCA5289589');
INSERT INTO `orders` VALUES ('ADF7B04B82074C478A2B2C203D0CD287', '2019-02-21 17:58:23', '15451.2', '4', '', '', '02740169E6E542B3A400DFBCA5289589');
INSERT INTO `orders` VALUES ('BF958CC6920441A694F83B73A962E650', '2019-02-21 18:00:59', '161.2', '3', '', '', '02740169E6E542B3A400DFBCA5289589');

-- ----------------------------
-- Table structure for `tb_user`
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `uid` char(32) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `email` char(50) NOT NULL,
  `code` char(64) NOT NULL,
  `state` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('02740169E6E542B3A400DFBCA5289589', '刘德华', '123', 'hezhuoming_123@sina.com', '301CCCABBA444EB8B6A7854E7256140F2FF8E794290E4D739C105D251AE5F464', '1');
INSERT INTO `tb_user` VALUES ('FE29C014065A4B35B797A26D63B4E8C2', '张学友', '456', '1139434884@qq.com', 'D6FCE48C4D5842A39836BB26D0E265510E28BC3D558748E0B913184603D65F60', '0');

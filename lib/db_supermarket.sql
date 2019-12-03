/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50534
Source Host           : localhost:3306
Source Database       : db_supermarket

Target Server Type    : MYSQL
Target Server Version : 50534
File Encoding         : 65001

Date: 2019-11-01 13:47:24
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_basicmessage
-- ----------------------------
DROP TABLE IF EXISTS `tb_basicmessage`;
CREATE TABLE `tb_basicmessage` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(10) CHARACTER SET utf8 DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `sex` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `dept` int(11) DEFAULT NULL,
  `headship` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of tb_basicmessage
-- ----------------------------
INSERT INTO `tb_basicmessage` VALUES ('7', '小陈', '27', '男', '1', '1');
INSERT INTO `tb_basicmessage` VALUES ('8', '小葛', '29', '男', '1', '1');
INSERT INTO `tb_basicmessage` VALUES ('16', '张三', '30', '男', '1', '5');
INSERT INTO `tb_basicmessage` VALUES ('23', '小开', '30', '男', '4', '4');
INSERT INTO `tb_basicmessage` VALUES ('24', '金额', '20', '女', '4', '7');
INSERT INTO `tb_basicmessage` VALUES ('25', 'cdd', '24', '女', '3', '6');
INSERT INTO `tb_basicmessage` VALUES ('27', '一一一', '25', '男', '2', '3');

-- ----------------------------
-- Table structure for tb_contact
-- ----------------------------
DROP TABLE IF EXISTS `tb_contact`;
CREATE TABLE `tb_contact` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `hid` int(11) DEFAULT NULL,
  `contact` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `officePhone` varchar(30) CHARACTER SET utf8 DEFAULT NULL,
  `fax` varchar(20) CHARACTER SET utf8 NOT NULL,
  `email` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `faddress` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of tb_contact
-- ----------------------------
INSERT INTO `tb_contact` VALUES ('2', '7', '1319431', '123', '123', '123', '123');
INSERT INTO `tb_contact` VALUES ('3', '8', '123', '123', '123', '撒地方', '123');
INSERT INTO `tb_contact` VALUES ('4', '9', '23', '234', '234234', '234', '234234');
INSERT INTO `tb_contact` VALUES ('5', '10', '234', '234', '234', '说的', '234');
INSERT INTO `tb_contact` VALUES ('6', '11', '234', '234', '234', '234', '234');
INSERT INTO `tb_contact` VALUES ('7', '13', '345', '345', '345', '345', '345');
INSERT INTO `tb_contact` VALUES ('8', '15', '23', '234', '234', '234', '234');
INSERT INTO `tb_contact` VALUES ('9', '16', '11212123', '123123', '123', '123', '123');
INSERT INTO `tb_contact` VALUES ('11', '18', '23', '23', '234486', '23', '234486');
INSERT INTO `tb_contact` VALUES ('12', '17', '12', '12', '12', '12', '12');
INSERT INTO `tb_contact` VALUES ('13', '0', '58949', '56222', '9959', '88@88.com', '9959');
INSERT INTO `tb_contact` VALUES ('14', '0', '6986', '6986', '69868', '69868', '69868');
INSERT INTO `tb_contact` VALUES ('15', '0', '266', '2322', '232', '2323', '232');
INSERT INTO `tb_contact` VALUES ('16', '19', '5465', '64', '45', '6', '45');
INSERT INTO `tb_contact` VALUES ('17', '19', '5465', '64', '45', '6', '45');
INSERT INTO `tb_contact` VALUES ('23', '22', '35434', '343', '3453', '345', '3453');
INSERT INTO `tb_contact` VALUES ('24', '23', '1454', '465', '456', '456', '长春');
INSERT INTO `tb_contact` VALUES ('25', '24', '456', '7485', '56', '46', '56');
INSERT INTO `tb_contact` VALUES ('27', '26', '44964', '566', '56', '88@88.com', '北京市');
INSERT INTO `tb_contact` VALUES ('28', '27', '456', '46', '46', '4', '长春市二道区');

-- ----------------------------
-- Table structure for tb_depot
-- ----------------------------
DROP TABLE IF EXISTS `tb_depot`;
CREATE TABLE `tb_depot` (
  `id` int(11) NOT NULL,
  `manage` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `functional` varchar(200) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of tb_depot
-- ----------------------------
INSERT INTO `tb_depot` VALUES ('1', '一一', '震荡');
INSERT INTO `tb_depot` VALUES ('2', '张三b ', '集合了。');
INSERT INTO `tb_depot` VALUES ('3', '小二黑', '精明能干');

-- ----------------------------
-- Table structure for tb_dept
-- ----------------------------
DROP TABLE IF EXISTS `tb_dept`;
CREATE TABLE `tb_dept` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dName` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `principal` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `bewrite` text CHARACTER SET utf8,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of tb_dept
-- ----------------------------
INSERT INTO `tb_dept` VALUES ('1', '收银部门', '小陈', '开发项目呵呵');
INSERT INTO `tb_dept` VALUES ('2', '销售部', '小肖肖', '无');
INSERT INTO `tb_dept` VALUES ('3', '订货', '小丁', '无');
INSERT INTO `tb_dept` VALUES ('4', '开发部', '小明', '无载');

-- ----------------------------
-- Table structure for tb_employee
-- ----------------------------
DROP TABLE IF EXISTS `tb_employee`;
CREATE TABLE `tb_employee` (
  `id` int(11) DEFAULT NULL,
  `dID` int(11) DEFAULT NULL,
  `eName` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `job` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `laborage` double DEFAULT NULL,
  `phone` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `QQNumber` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `email` varchar(50) CHARACTER SET utf8 DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of tb_employee
-- ----------------------------

-- ----------------------------
-- Table structure for tb_headship
-- ----------------------------
DROP TABLE IF EXISTS `tb_headship`;
CREATE TABLE `tb_headship` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `headshipName` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of tb_headship
-- ----------------------------
INSERT INTO `tb_headship` VALUES ('1', '总经理');
INSERT INTO `tb_headship` VALUES ('2', '副总经理');
INSERT INTO `tb_headship` VALUES ('3', '总监');
INSERT INTO `tb_headship` VALUES ('4', '大区经理');
INSERT INTO `tb_headship` VALUES ('5', '小区经理');
INSERT INTO `tb_headship` VALUES ('6', '主管');
INSERT INTO `tb_headship` VALUES ('7', '普通员工');

-- ----------------------------
-- Table structure for tb_joindepot
-- ----------------------------
DROP TABLE IF EXISTS `tb_joindepot`;
CREATE TABLE `tb_joindepot` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `oid` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `dId` int(11) DEFAULT NULL,
  `wareName` varchar(40) CHARACTER SET utf8 DEFAULT NULL,
  `joinTime` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `weight` double DEFAULT NULL,
  `remark` varchar(200) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of tb_joindepot
-- ----------------------------
INSERT INTO `tb_joindepot` VALUES ('3', '1256', '2', '小陈双', '2011-08-10 14:27:06', '10', '无');
INSERT INTO `tb_joindepot` VALUES ('4', '1029', '1', '小王', '2011-08-10 14:27:12', '23', '无');
INSERT INTO `tb_joindepot` VALUES ('5', '1001', '2', '什么', '2011-08-10 14:35:25', '10000', '载震荡');
INSERT INTO `tb_joindepot` VALUES ('9', '1002', '3', '随便一', '2011-08-11 13:46:20', '90', '无');
INSERT INTO `tb_joindepot` VALUES ('13', 'testoid', '2222', 'test', 'test', '12.5', 'test');
INSERT INTO `tb_joindepot` VALUES ('14', '1026', '1', '小李', '2014-04-08 15:25:14', '10', '无');
INSERT INTO `tb_joindepot` VALUES ('15', '1026', '3', '小李', '2014-04-08 15:25:38', '10', '无');
INSERT INTO `tb_joindepot` VALUES ('16', '1026', '1', '小李', '2014-04-08 15:26:00', '10', '无');

-- ----------------------------
-- Table structure for tb_outdepot
-- ----------------------------
DROP TABLE IF EXISTS `tb_outdepot`;
CREATE TABLE `tb_outdepot` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `did` int(11) DEFAULT NULL,
  `wName` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `outDate` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `wight` double DEFAULT NULL,
  `remark` text CHARACTER SET utf8,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of tb_outdepot
-- ----------------------------
INSERT INTO `tb_outdepot` VALUES ('1', '2', '桶装方便面', '2011-05-25 16:38:40', '1', '12');
INSERT INTO `tb_outdepot` VALUES ('2', '2', '桶装方便面', '2011-07-21 10:45:25', '2', '2');
INSERT INTO `tb_outdepot` VALUES ('3', '3', '随便一', '2011-08-11', '10', '无');

-- ----------------------------
-- Table structure for tb_provide
-- ----------------------------
DROP TABLE IF EXISTS `tb_provide`;
CREATE TABLE `tb_provide` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cName` varchar(20) CHARACTER SET utf8 NOT NULL,
  `address` varchar(40) CHARACTER SET utf8 DEFAULT NULL,
  `linkman` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `linkPhone` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `faxes` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `postNum` varchar(10) CHARACTER SET utf8 DEFAULT NULL,
  `bankNum` varchar(30) CHARACTER SET utf8 DEFAULT NULL,
  `netAddress` varchar(30) CHARACTER SET utf8 DEFAULT NULL,
  `emaillAddress` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `remark` varchar(200) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of tb_provide
-- ----------------------------
INSERT INTO `tb_provide` VALUES ('4', '客户名称', '客户地址', '不知', '46546', '2226', '26526', '5226556', '网址', '88@88.com', '备注');
INSERT INTO `tb_provide` VALUES ('5', '央达有限公司', '长春', '央达', '11111', '39696', '69696', '6969', 'www.mingri.com', '88@88.com', '无');
INSERT INTO `tb_provide` VALUES ('6', '中小', '长春', '中中', '1556', '566', '156', '45646', '546', '88@88.com', 'fq ');

-- ----------------------------
-- Table structure for tb_sell
-- ----------------------------
DROP TABLE IF EXISTS `tb_sell`;
CREATE TABLE `tb_sell` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sellName` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `address` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `linkman` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `linkPhone` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `faxNum` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `postNum` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `bankNum` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `netAddress` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `emaillAddress` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `remark` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of tb_sell
-- ----------------------------
INSERT INTO `tb_sell` VALUES ('6', '销售商名称', '长春', '赛总', '57585', '58585', '58585', '585858', '', '88@88.com', '无无无');
INSERT INTO `tb_sell` VALUES ('7', '长销', '长春', '销一', '546', '45646', '454', '564', '41564596', '88@88.com', '无');

-- ----------------------------
-- Table structure for tb_stock
-- ----------------------------
DROP TABLE IF EXISTS `tb_stock`;
CREATE TABLE `tb_stock` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sName` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `orderId` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `consignmentDate` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `baleName` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `count` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `money` double DEFAULT NULL,
  `unrukucount` double DEFAULT NULL,
  `isrukufinish` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of tb_stock
-- ----------------------------
INSERT INTO `tb_stock` VALUES ('1', '桶装方便面', '4563', '456', '456', '45', '456', null, null);
INSERT INTO `tb_stock` VALUES ('2', '小葛', '1024', '2011-6-24', '软面包', '250', '1200', null, null);
INSERT INTO `tb_stock` VALUES ('3', '小李', '1026', '2011-8-7', '咸菜', '10', '150', null, null);
INSERT INTO `tb_stock` VALUES ('4', '小王', '1029', '2011-8-5', '饼干', '23', '250', null, null);
INSERT INTO `tb_stock` VALUES ('5', '小陈双', '1256', '2011-6-29', '巧克力', '10', '256', null, null);
INSERT INTO `tb_stock` VALUES ('6', '什么', '1001', '2011-08-10', '水杯', '100', '1000', null, null);
INSERT INTO `tb_stock` VALUES ('8', '随便一', '1002', '2011-08-11', '毛巾', '100', '1000', null, null);

-- ----------------------------
-- Table structure for tb_users
-- ----------------------------
DROP TABLE IF EXISTS `tb_users`;
CREATE TABLE `tb_users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `passWord` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of tb_users
-- ----------------------------
INSERT INTO `tb_users` VALUES ('1', 'mr', 'mrsoft');

-- ----------------------------
-- Table structure for tb_ware
-- ----------------------------
DROP TABLE IF EXISTS `tb_ware`;
CREATE TABLE `tb_ware` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `wareName` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `warBewrite` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `spec` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `stockPrice` double DEFAULT NULL,
  `retailPrice` double DEFAULT NULL,
  `associatorPrice` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of tb_ware
-- ----------------------------
INSERT INTO `tb_ware` VALUES ('4', '手机', '超长待机', '部', '2500', '2600', '2500');
INSERT INTO `tb_ware` VALUES ('5', '毛巾', '厚毛巾', '条', '13.5', '15', '13.5');
INSERT INTO `tb_ware` VALUES ('8', '饼干', '奶油味', '包', '12.3', '15.9', '15');
INSERT INTO `tb_ware` VALUES ('9', '洗发水', '海飞丝(400ml)', '瓶', '19', '28', '27');

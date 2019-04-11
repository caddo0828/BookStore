/*
Navicat MySQL Data Transfer

Source Server         : im
Source Server Version : 50547
Source Host           : localhost:3306
Source Database       : tq

Target Server Type    : MYSQL
Target Server Version : 50547
File Encoding         : 65001

Date: 2019-04-11 09:26:28
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for books
-- ----------------------------
DROP TABLE IF EXISTS `books`;
CREATE TABLE `books` (
  `id` varchar(100) CHARACTER SET utf8 NOT NULL,
  `name` varchar(200) CHARACTER SET utf8 NOT NULL,
  `price` double NOT NULL,
  `category` varchar(40) CHARACTER SET utf8 DEFAULT NULL,
  `nums` int(100) NOT NULL DEFAULT '1000',
  `imgurl` varchar(100) CHARACTER SET utf8 NOT NULL,
  `description` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `author` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of books
-- ----------------------------
INSERT INTO `books` VALUES ('26ddd0c6-d7f2-4a83-b01b-286bb8c38420', '.net设计规范', '50', '计算机', '0', 'static/images/book/bba5ddf2-2a56-4a88-9d1b-abc8e0202ec9.png', '学好编程,走遍天下', '王厚淳');
INSERT INTO `books` VALUES ('3a0196b2-71c1-4d4d-a4e8-d1f875096f00', '网管员必备宝典', '20', '计算机', '0', 'static/images/book/0270eba2-2b48-48df-956b-0341204384d9.png', '计算机类', '宁在早');
INSERT INTO `books` VALUES ('3cdd01d2-95d4-4077-b512-ff4c3b340d6b', '学会宽容', '25', '文学', '277', 'static/images/book/a2da626c-c72d-4972-83de-cf48405c5563.png', '该书阐述了宽容是一种智慧和力量，是对生命的洞悉，是成长的绿荫，更是家庭幸福的秘诀。常用宽容的眼光看世界，事业、家庭和友谊才能稳固和长久。', '余华');
INSERT INTO `books` VALUES ('4dbac6bb-ac2a-4ea0-a62f-ea5a2fc2b3c2', '杜拉拉升职记', '54', '原版', '186', 'static/images/book/c4ab442f-95c7-4d6f-a57e-3eb7dc6b83c4.png', '职场生活', '韩曙平');
INSERT INTO `books` VALUES ('67a44950-935a-4dac-953d-515fd92d8174', '时空穿行', '42', '科技', '187', 'static/images/book/6cc3c25b-2475-496e-9ad7-5e9491e7aaf8.png', '《时空穿行(中国乡村人类学世纪回访)》对20世纪80年代以来中国云南大理西镇、广东潮州凤凰村、华南茶山等八个代表性乡村田野进行调查，探讨了中国乡村文化的多样性、宗族制度、农民社会等课题。', '李娜');
INSERT INTO `books` VALUES ('72c52302-cd1e-4a22-8ac8-dc300a915be5', '雪中悍刀行', '59', '小说', '85', 'static/images/book/81967f4f-0a39-4b03-8ecc-053365a35605.png', '如何教育孩子', '刘甜');
INSERT INTO `books` VALUES ('79bbe618-d2f8-4081-b35a-62ebbe938b64', 'Java基础入门', '44.5', '计算机', '6', 'static/images/book/697a23d6-225a-41a3-8c20-7ab624265ecc.png', '《Java基础入门》从初学者的角度详细讲解了Java开发中重点用到的多种技术。全书共11章，包括Java开发环境的搭建及其运行机制、基本语法、面向对象的思想，采用典型翔实的例子、通俗易懂的语言阐述面向对象中的抽象概念。在多线程、常用API、集合、IO、GUI、网络编程章节中，通过剖析案例、分析代码结构含义、解决常见问题等方式，帮助初学者培养良好的编程习惯。最后，讲解了Eclipse开发工具，帮助初学者熟悉开发工具的使用。', '刘意');
INSERT INTO `books` VALUES ('84c842da-16b6-4e87-953e-859a1ca62bab', '赢在影响力', '89', '励志', '43', 'static/images/book/acc9d557-f8c9-426b-9aec-50a5a7cf3960.png', '《赢在影响力：人际交往的学问》创造了全球出版史上空前的发行记录。它深深地触动着读者的神经，满足了他们在人性方面的需要，因此成为经济萧条后期超越流行的读物。它居高不下的销售记录一直持续至20世纪80年代，经历了几乎半个世纪。', '张年如');
INSERT INTO `books` VALUES ('8740bee0-bfb8-4ba1-8f6e-b69310617db9', '大勇和小花的欧洲日记', '26', '生活百科', '93', 'static/images/book/3253aeee-5462-47d0-991c-afb568ab3b03.png', '《大勇和小花的欧洲日记》串起了欧洲文明的溯源之旅。从屹立在近现代艺术之巅的巴黎拾级而下，依次是蓬皮杜博物馆（现代艺术）、奥塞博物馆（印象派艺术）、卢浮宫博物馆（古典主义）。在这里，蒙娜丽莎向文艺复兴的故乡微笔，那是意大利的佛罗伦萨。达芬奇、米开朗琪罗、拉菲尔等巨匠们开创了文艺复兴运动，他们心中的圣地是希腊，似乎已到了起源。然而，这并未到头，与这源头对接的还有遥远的希腊神话，其中有大西洲的传说。在希腊小岛圣托里尼，你看见Atlantica酒店吗？那是人们为大西洲刻下的念想。', '李娟');
INSERT INTO `books` VALUES ('880fdb00-6798-4302-962b-f337f3393802', 'Java Web程序开发入门', '44.5', '计算机', '99', 'static/images/book/eac105d4-4ab5-4af9-9061-e255016b79d9.png', '本书为Java Web开发入门教材，让初学者达到能够灵活使用Java语言开发Web应用程序的程度。为了让初学者易于学习，本书力求内容通俗易懂，讲解寓教于乐，同时针对书中的每个知识点，都精心设计了经典案例，让初学者真正理解这些知识点在实际工作中如何去运用。', '唐龙昌');
INSERT INTO `books` VALUES ('8c19d2e2-1020-425d-aeeb-56d3c1bc0a81', '谁动了我的奶酪', '26', '少儿', '197', 'static/images/book/ebcee924-7d42-43f8-b974-fbdb900bdb58.png', '儿童作品', '孙杨');
INSERT INTO `books` VALUES ('968df3a0-be4d-473e-85a8-d3466b4ca7c2', '别做正常的傻瓜', '18', '励志', '35', 'static/images/book/792116e7-6d83-4be4-b3e5-4dd11b0b4565.png', '本书结合了作者十余年的教学经验，融合了诺贝尔奖得主及其他学者数十年的研究成果，用深入浅出的方法帮助你发现自己决策中的误区，从而使你比大多数人少几分正常，多几分理性，本书所涉及的决策范围广，包括购买什么商品，和什么人结婚，雇用哪些员工，投资什么股票等等。除非你从不做决策，否则本书对你一定有所借鉴。', '刘善慧');
INSERT INTO `books` VALUES ('9a7af97e-deea-417e-ad66-23ea755d2a51', '培育男孩', '25', '生活', '7', 'static/images/book/7ede11f8-3ff9-4c12-ad58-b1d5e0e72032.png', '本书是美国著名家庭问题和儿童教育问题专家、畅销书作家詹姆士·杜布森的新作。全书围绕值得令人类关注的培育男孩问题，讨论了家庭教育、学校教育、父母关爱，以及整个社会文化对男孩成长的影响。针对美国社会普遍存在的男孩教育问题，如父亲缺席、母亲上班、暴力泛滥、单亲家庭、同性恋、学校对男孩特点的忽视等等，提出了自己的看法和解决思路，具有很强的指导性和可操作性，是父母、教师和青年工作者的必读参考书。', '林珍娜');
INSERT INTO `books` VALUES ('adc06981-0dc9-4006-b763-e6413ee93ac1', '培育女孩', '20', '生活', '13', 'static/images/book/5394df32-ed6e-4203-b9c0-fc175cfc187e.png', '无', '张基龙');
INSERT INTO `books` VALUES ('bf66a00c-4a78-458b-93c8-08896ee14976', '美国纽约摄影学院摄影教材', '45', '艺术', '99', 'static/images/book/20788b05-d298-4a7c-91d9-370fb056f6a5.png', '艺术教材', 'Jordan Smith');
INSERT INTO `books` VALUES ('c85d68d3-5758-494e-8dcf-f4f84d852949', '系统分析师教程', '54', '考试', '300', 'static/images/book/e4d290ce-3355-466f-a51e-13c62552d2cb.png', '系统分析师', 'KenWenKim');
INSERT INTO `books` VALUES ('cb22386f-0b77-454c-976f-d6417ad72613', '中国国家地理', '20', '学术', '20', 'static/images/book/2105fbe5-400f-4193-a7db-d7ebac389550.png', '《中国国家地理》，原名《地理知识》，是关于地理的月刊，该刊的文章和图片经常被中央及地方媒体转载。具有很强的可读性和收藏价值，国内外很多家图书馆已经把该刊作为重点收藏期刊。内容以中国地理为主，兼具世界各地不同区域的自然、人文景观和事件，并揭示其背景和奥秘，另亦涉及天文、生物、历史和考古等领域。是中国大陆著名的有关地理的杂志。因该社隶属中国科学院，有一大批自然地理和人文地理的专家学者作为该社顾问，同时还有许多战斗在科考第一线的工作者与杂志社保持着密切联系，因此具有很强的独家性和权威性。', '鲁燕飞');
INSERT INTO `books` VALUES ('bdb32537-8f2c-4ba2-a752-94fdc0e9a250', '经济案例解析', '35', '经营', '96', 'static/images/book/8efe720f-fa72-435b-a3c3-69230f9677cc.png', '深度解析当今的经济形式', '谭亮');
INSERT INTO `books` VALUES ('000A18FDB38F470DBE9CD0972BADB23F', 'Java Web整合开发实战--基于Struts 2+Hibernate+Spring', '79.8', '计算机', '630', 'static/images/book/23268958-1_w.jpg', '（99个应用实例，4个项目开发案例，15.5小时配套教学视频，超值DVD光盘含大量开发资源。一本书搞定SSH框架整合开发！）', '贾蓓');
INSERT INTO `books` VALUES ('0BE0707984014E66BD9F34F534FCE0F7', 'OSGi实战', '99', '计算机', '465', 'static/images/book/22938396-1_w.jpg', '【OSGi规范制定者亲自撰写 汇集Apache项目技术实战经验】', 'Richard');
INSERT INTO `books` VALUES ('0EE8A0AE69154287A378FB110FF2C780', 'Java核心技术', '98', '文学', '691', 'static/images/book/20285763-1_w.jpg', '卷Ⅰ基础知识（原书第8版）', '昊斯特曼');
INSERT INTO `books` VALUES ('113D0D1BB9174DD19A7DE7E2B37F677F', 'Java7入门经', '118', '原版', '1122', 'static/images/book/22813026-1_w.jpg', '（跟编程导师Ivor Horton学Java 预计到货日期9月初）', '霍尔顿');
INSERT INTO `books` VALUES ('1286B13F0EA54E4CB75434762121486A', 'Java核心技术', '109', '科技', '20', 'static/images/book/23280479-1_w.jpg', ' 卷I：基础知识(第9版·英文版)(上、下册)', '霍斯特曼');
INSERT INTO `books` VALUES ('13EBF9B1FDE346A683A1C45BD6773ECB', 'Java开发实战1200例', '99', '小说', '0', 'static/images/book/21110930-1_w_1.jpg', '（第Ⅱ卷）（史上最全的“编程实例”类图书，代码分析、实例速查、练习巩固的绝好帮手）', '无');
INSERT INTO `books` VALUES ('1A15DC5E8A014A58862ED741D579B530', 'Java深入解析——透析Java本质的36个话题', '49', '计算机', '298', 'static/images/book/23363997-1_w_1.jpg', '透析Java本质的36个话题', '梁勇');
INSERT INTO `books` VALUES ('210A3DCA429049C78B623C3986BEB136', 'JavaScript经典教程套装：JavaScript高级程序设计(第3版)+JavaScript DOM编程艺术(第2版)(套装共2册)(超值附赠《码农》光盘1张)', '148', '励志', '1048', 'static/images/book/23224089-1_w.jpg', 'JavaScript高级程序设计(第3版)+JavaScript DOM编程艺术(第2版)(套装共2册)(超值附赠《码农》光盘1张)', 'Nicholas C. Zakas');
INSERT INTO `books` VALUES ('22234CECF15F4ECB813F0B433DDA5365', 'JavaScript从入门到精通（附光盘1张）', '69.8', '生活百科', '532', 'static/images/book/22862057-1_w.jpg', '（连续8月JavaScript类全国零售排行前2名，13小时视频，400个经典实例、369项面试真题、138项测试史上最全资源库）', '明日科技');
INSERT INTO `books` VALUES ('230A00EC22BF4A1DBA87C64800B54C8D', 'Struts2技术内幕', '69', '计算机', '379', 'static/images/book/22577578-1_w.jpg', '深入解析Struts架构设计与实现原理', '陆舟');
INSERT INTO `books` VALUES ('260F8A3594F741C1B0EB69616F65045B', 'Tomcat与Java Web开发技术详解（第2版）(含光盘1张)', '79.5', '少儿', '732', 'static/images/book/20420983-1_w.jpg', '高级特性（原书第8版）', '孙卫琴');
INSERT INTO `books` VALUES ('28A03D28BAD449659A77330BE35FCD65', 'JAVA核心技术卷II', '118', '励志', '852', 'static/images/book/20446562-1_w.jpg', '(Java并发编程领域的里程碑之作，资深Java技术专家、并发编程专家、敏捷开发专家和Jolt大奖得主撰写，Amazon五星畅销书)', '霍斯特曼');
INSERT INTO `books` VALUES ('2EE1A20A6AF742E387E18619D7E3BB94', 'Java虚拟机并发编程', '59', '生活', '214', 'static/images/book/23239786-1_w.jpg', null, 'Venkat Subramaniam');
INSERT INTO `books` VALUES ('33ACF97A9A374352AE9F5E89BB791262', '基于MVC的JavaScript Web富应用开发', '59', '生活', '282', 'static/images/book/22757564-1_w.jpg', null, '麦卡劳');
INSERT INTO `books` VALUES ('37F75BEAE1FE46F2B14674923F1E7987', '数据结构与算法分析Java语言描述 第2版', '55', '艺术', '440', 'static/images/book/20417467-1_w.jpg', null, '韦斯');
INSERT INTO `books` VALUES ('39F1D0803E8F4592AE1245CACE683214', 'Java程序员修炼之道', '89', '考试', '395', 'static/images/book/23301847-1_w_1.jpg', null, '埃文斯');
INSERT INTO `books` VALUES ('3AE5C8B976B6448A9D3A155C1BDE12BC', '深入理解Java虚拟机', '69', '学术', '0', 'static/images/book/21108671-1_w_1.jpg', 'JVM高级特性与最佳实践（超级畅销书，6个月5次印刷，从实践角度解析JVM工作原理，Java程序员必备）', '周志明');
INSERT INTO `books` VALUES ('3DD187217BF44A99B86DD18A4DC628BA', 'Java核心技术 卷1 基础知识（原书第9版）', '119', '计算机', '704', 'static/images/book/23362142-1_w_1.jpg', null, '霍斯特曼，科内尔');
INSERT INTO `books` VALUES ('3E1990E19989422E9DA735978CB1E4CE', 'Effective Java中文版(第2版)', '52', '计算机', '287', 'static/images/book/20459091-1_w.jpg', null, '布洛克');
INSERT INTO `books` VALUES ('400D94DE5A0742B3A618FC76DF107183', 'JavaScript宝典（第7版）（配光盘）', '128', '文学', '1012', 'static/images/book/23169892-1_w.jpg', null, '古德曼');
INSERT INTO `books` VALUES ('4491EA4832E04B8B94F334B71E871983', 'Java语言程序设计：进阶篇（原书第8版）', '79', '原版', '507', 'static/images/book/21117631-1_w_1.jpg', null, '梁勇');
INSERT INTO `books` VALUES ('48BBFBFC07074ADE8CC906A45BE5D9A6', 'JavaScript权威指南（第6版）', '139', '科技', '1002', 'static/images/book/22722790-1_w.jpg', '（淘宝前端团队倾情翻译！经典权威的JavaScript犀牛书！第6版特别涵盖了HTML5和ECMAScript5！）（经典巨著，当当独家首发）', '弗兰纳根');
INSERT INTO `books` VALUES ('49D98E7916B94232862F7DCD1B0BAB66', 'HTML5+JavaScript动画基础', '69', '小说', '392', 'static/images/book/23266633-1_w.jpg', null, '兰贝塔');
INSERT INTO `books` VALUES ('4A9574F03A6B40C1B2A437237C17DEEC', 'Spring实战(第3版)', '59', '计算机', '374', 'static/images/book/23254532-1_w.jpg', '（In Action系列中最畅销的Spring图书，近十万读者学习Spring的共同选择）', 'Craig Walls');
INSERT INTO `books` VALUES ('4BF6D97DD18A4B77B8DED9B057577F8F', 'Java Web从入门到精通', '69.8', '励志', '547', 'static/images/book/22862056-1_w.jpg', '（附光盘1张）（连续8月Java类全国零售排行前2名，27小时视频，951个经典实例、369项面试真题、596项测试史上最全资源库）', '明日科技');
INSERT INTO `books` VALUES ('4C3331CAD5A5453787A94B8D7CCEAA29', 'Java Web整合开发王者归来', '99.8', '生活百科', '1010', 'static/images/book/20756351-1_w_1.jpg', '（JSP+Servlet+Struts+Hibernate+Spring）（配光盘', '刘京华');
INSERT INTO `books` VALUES ('4D20D2450B084113A331D909FF4975EB', 'jQuery实战(第2版)(畅销书升级版，掌握Web开发利器必修宝典)', '69', '计算机', '394', 'static/images/book/22638286-1_w.jpg', '（《疯狂Java讲义》最佳拍档，扫清知识死角，夯实基本功）', 'Bear Bibeault　Yehuda Katz ');
INSERT INTO `books` VALUES ('4E44405DAFB7413E8A13BBFFBEE73AC7', 'JavaScript经典实例', '78', '少儿', '512', 'static/images/book/22692811-1_w.jpg', '（Web开发首选实时 跨多服务器 高并发）', '鲍尔斯');
INSERT INTO `books` VALUES ('504FB999B0444B339907090927FDBE8A', '深入浅出Ext JS(第3版)', '69', '励志', '413', 'static/images/book/23351049-1_w_1.jpg', null, '徐会生');
INSERT INTO `books` VALUES ('52077C8423B645A9BADA96A5E0B14422', 'Spring源码深度解析', '69', '生活', '386', 'static/images/book/23329703-1_w_1.jpg', null, '郝佳');
INSERT INTO `books` VALUES ('52B0EDFF966E4A058BDA5B18EEC698C4', '亮剑Java Web项目开发案例导航(含DVD光盘1张)', '69', '生活', '526', 'static/images/book/22623766-1_w.jpg', null, '朱雪琴');
INSERT INTO `books` VALUES ('5315DA60D24042889400AD4C93A37501', 'Spring 3.x企业应用开发实战(含CD光盘1张)', '90', '艺术', '710', 'static/images/book/22605701-1_w.jpg', null, '陈雄华');
INSERT INTO `books` VALUES ('56B1B7D8CD8740B098677C7216A673C4', '疯狂 Java 程序员的基本修养', '59', '考试', '484', 'static/images/book/23042420-1_w.jpg', null, '李刚');
INSERT INTO `books` VALUES ('57B6FF1B89C843C38BA39C717FA557D6', '了不起的Node.js: 将JavaScript进行到底', '79', '学术', '292', 'static/images/book/23368351-1_w_2.jpg', null, 'Guillermo Rauch');
INSERT INTO `books` VALUES ('5C4A6F0F4A3B4672AD8C5F89BF5D37D2', 'Java从入门到精通（第3版）', '59.8', '经营', '564', 'static/images/book/22862060-1_w.jpg', '（附光盘1张）（连续8月Java类全国零售排行前2名，32小时视频，732个经典实例、369项面试真题、616项测试史上最全资源库）', '明日科技');
INSERT INTO `books` VALUES ('5C68141786B84A4CB8929A2415040739', 'JavaScript高级程序设计(第3版)(JavaScript技术名著，国内JavasScript第一书，销量超过8万册)', '99', '计算机', '730', 'static/images/book/22628333-1_w.jpg', null, 'Nicholas C. Zakas');
INSERT INTO `books` VALUES ('5EDB981339C342ED8DB17D5A198D50DC', 'Java程序性能优化', '59', '计算机', '400', 'static/images/book/22881618-1_w.jpg', null, '葛一鸣');
INSERT INTO `books` VALUES ('6398A7BA400D40258796BCBB2B256068', 'JavaScript设计模式', '49', '计算机', '241', 'static/images/book/23266635-1_w.jpg', null, 'Addy Osmani');
INSERT INTO `books` VALUES ('676B56A612AF4E968CF0F6FFE289269D', 'JavaScript和jQuery实战手册（原书第2版）', '99', '原版', '504', 'static/images/book/23201813-1_w.jpg', null, '麦克法兰');
INSERT INTO `books` VALUES ('7917F5B19A0948FD9551932909328E4E', 'Java项目开发案例全程实录（第2版）（配光盘）（软件项目开发全程实录丛书）', '69.8', '科技', '605', 'static/images/book/20991549-1_w_1.jpg', null, '明日科技');
INSERT INTO `books` VALUES ('7C0C785FFBEC4DEC802FA36E8B0BC87E', '深入分析Java Web技术内幕', '69', '小说', '440', 'static/images/book/22881803-1_w.jpg', null, '许令波');
INSERT INTO `books` VALUES ('7CD79C20258F477AB841518D9312E843', 'Java程序员面试宝典（第三版）', '49', '计算机', '359', 'static/images/book/23348683-1_w_1.jpg', null, '欧立奇');
INSERT INTO `books` VALUES ('7D7FE81293124793BDB2C6FF1F1C943D', '21天学通Java(第6版)（中文版累计销量超30000册）', '55', '励志', '407', 'static/images/book/23219731-1_w.jpg', null, 'Rogers Cadenhead');
INSERT INTO `books` VALUES ('7FD7F50B15F74248AA769798909F8653', 'Java网络编程（第3版）——O’Reilly Java系列', '85', '生活百科', '718', 'static/images/book/9062293-1_w.jpg', null, '哈诺德');
INSERT INTO `books` VALUES ('819FF56E4423462394E6F83882F78975', '学通Java Web的24堂课（配光盘）（软件开发羊皮卷）', '79.8', '计算机', '718', 'static/images/book/21118835-1_w_1.jpg', null, '陈丹丹');
INSERT INTO `books` VALUES ('81FADA99309342F4978D5C680B0C6E8C', 'Java入门很简单（配光盘）', '59.8', '少儿', '459', 'static/images/book/22839309-1_w.jpg', null, '李世民');
INSERT INTO `books` VALUES ('89A57D099EA14026A5C3D10CFC10C22C', 'Java 2实用教程（第4版）', '39.5', '励志', '479', 'static/images/book/22844118-1_w.jpg', null, '耿祥义');
INSERT INTO `books` VALUES ('8A5B4042D5B14D6B87A34DABF327387F', 'Java核心技术 卷II：高级特性(第9版·英文版)(上、下册)', '119', '生活', '1118', 'static/images/book/23280478-1_w.jpg', null, '霍斯特曼');
INSERT INTO `books` VALUES ('8DD0ADF2665B40899E09ED2983DC3F7B', 'jQuery权威指南', '59', '生活', '385', 'static/images/book/21006995-1_w_1.jpg', null, '陶国荣');
INSERT INTO `books` VALUES ('8E16D59BA4C34374A68029AE877613C4', '轻量级Java EE企业应用实战', '99', '艺术', '816', 'static/images/book/22685703-1_w.jpg', null, '李刚');
INSERT INTO `books` VALUES ('8F1520F2CED94C679433B9C109E791CB', 'Java从入门到精通（实例版）', '69.8', '考试', '548', 'static/images/book/22862061-1_w.jpg', '（附光盘1张）（连续8月Java类全国零售排行前2名，14小时视频，732个经典实例、369项面试真题、616项测试史上最全资源库）', '明日科技');
INSERT INTO `books` VALUES ('90E423DBE56042838806673DB3E86BD3', '《Spring技术内幕（第2版）》', '69', '学术', '399', 'static/images/book/22606836-1_w.jpg', '（畅销书全新升级，Spring类图书销量桂冠，从宏观和微观两个角度解析Spring架构设计和实现原理）', '计文柯');
INSERT INTO `books` VALUES ('926B8F31C5D04F61A72F66679A0CCFFD', 'JavaScript编程精解（华章程序员书库）', '49', '经营', '162', 'static/images/book/22873894-1_w.jpg', '（JavaScript之父高度评价并强力推荐，系统学习JS首选！）', '哈弗贝克');
INSERT INTO `books` VALUES ('95AACC68D64D4D67B1E33E9EAC22B885', 'Head First Java（中文版）', '79', '计算机', '689', 'static/images/book/9265169-1_w.jpg', '（JAVA经典畅销书 生动有趣 轻松学好JAVA）', '塞若');
INSERT INTO `books` VALUES ('97437DAD03FA456AA7D6154614A43B55', 'HTML、CSS、JavaScript网页制作从入门到精通', '49', '原版', '450', 'static/images/book/22928649-1_w.jpg', '两万读者的选择，经久不衰的超级畅销书最新升级版！网页制作学习者入门必读经典！', '刘西杰');
INSERT INTO `books` VALUES ('9923901FBF124623BC707920D8936BC8', 'JavaScript DOM编程艺术(第2版)', '49', '科技', '286', 'static/images/book/21049601-1_w_1.jpg', null, '基思');
INSERT INTO `books` VALUES ('99BF63AC12AD48FCB673F1820888964E', 'Java Web开发实战1200例（第Ⅱ卷）', '99', '计算机', '0', 'static/images/book/21110929-1_w_1.jpg', '（史上最全的“编程实例”类图书，代码分析、实例速查、练习巩固的绝好帮手）', '无');
INSERT INTO `books` VALUES ('9D257176A6934CB79427CEC37E69249F', '疯狂Ajax讲义（第3版）', '79', '原版', '624', 'static/images/book/23184673-1_w.jpg', '--jQuery/Ext JS/Prototype/DWR企业应用前端开发实战(含CD光盘1张)（畅销书升级版，企业应用前端开发实战指南）', '李刚');
INSERT INTO `books` VALUES ('9FBD51A7C02D4F5B9862CD2EBBF5CA04', 'Flash ActionScript 3.0全站互动设计', '69.8', '科技', '488', 'static/images/book/22886581-1_w.jpg', null, '刘欢 ');
INSERT INTO `books` VALUES ('9FF423101836438F874035A48498CF45', 'Java编程思想（英文版·第4版）', '79', '计算机', '1482', 'static/images/book/9288920-1_w.jpg', null, '埃克尔 ');
INSERT INTO `books` VALUES ('A3D464D1D1344ED5983920B472826730', 'Java Web开发详', '119', '原版', '889', 'static/images/book/22788412-1_w.jpg', '解：XML+DTD+XML Schema+XSLT+Servlet 3 0+JSP 2 2深入剖析与实例应用(含CD光盘1张)', '孙鑫');
INSERT INTO `books` VALUES ('A46A0F48A4F649AE9008B38EA48FAEBA', 'Java编程全能词典(含DVD光盘2张)', '98', '科技', '486', 'static/images/book/20813806-1_w_1.jpg', null, '明日科技');
INSERT INTO `books` VALUES ('A5A6F27DCD174614850B26633A0B4605', 'JavaScript模式', '38', '计算机', '208', 'static/images/book/22819430-1_w.jpg', null, '斯特凡洛夫');
INSERT INTO `books` VALUES ('A7220EF174704012830E066FDFAAD4AD', 'Spring 3.0就这么简单', '59', '计算机', '380', 'static/images/book/22938474-1_w.jpg', '（国内原创的Java敏捷开发图书，展现作者Spring原创开源项目ROP开发的全过程，所有项目工程均以Maven组织）', '陈雄华');
INSERT INTO `books` VALUES ('A7EFD99367C9434682A790635D3C5FDF', 'Java Web技术整合应用与项目实战', '98', '文学', '878', 'static/images/book/23266270-1_w.jpg', '（JSP+Servlet+Struts2+Hibernate+Spring3）', '张志锋');
INSERT INTO `books` VALUES ('A8EF76FD21A645109538614DEA85F3F7', 'Java语言程序设计：基础篇（原书第8版）', '75', '原版', '586', 'static/images/book/21122188-1_w_1.jpg', null, '梁勇');
INSERT INTO `books` VALUES ('AD6EA79CCB8240AAAF5B292AD7E5DCAA', 'jQuery Mobile权威指南', '59', '科技', '249', 'static/images/book/22847009-1_w.jpg', '（根据jQuery Mobile最新版本撰写的权威参考书！全面讲解jQuery Mobile的所有功能、特性、使用方法和开发技巧）', '陶国荣');
INSERT INTO `books` VALUES ('AE0935F13A214436B8599DE285A86220', 'JavaScript基础教程(第8版)(经典JavaScript入门书 涵盖Ajax和jQuery)', '69', '小说', '392', 'static/images/book/22717349-1_w.jpg', null, 'Tom Negrino　Dori Smith');
INSERT INTO `books` VALUES ('AF28ED8F692C4288B32CF411CBDBFC23', '经典Java EE企业应用实战', '79', '计算机', '0', 'static/images/book/20928547-1_w_1.jpg', '基于WebLogic/JBoss的JSF+EJB 3+JPA整合开发(含CD光盘1张)', '无');
INSERT INTO `books` VALUES ('B329A14DDEF8455F82B3FDF25821D2BB', '名师讲坛——Java Web开发实战经典基础篇', '69.8', '励志', '554', 'static/images/book/20915948-1_w_3.jpg', '（JSP、Servlet、Struts、Ajax）（32小时全真课堂培训，视频超级给力！390项实例及分析，北京魔乐科技培训中心Java Web全部精华）', '李兴华');
INSERT INTO `books` VALUES ('B7A7DA7A94E54054841EED1F70C3027C', '锋利的jQuery(第2版)(畅销书升级版，增加jQuery Mobile和性能优化)', '49', '生活百科', '380', 'static/images/book/22786088-1_w.jpg', null, '单东林');
INSERT INTO `books` VALUES ('BD1CB005E4A04DCA881DA8689E21D4D0', 'jQuery UI开发指南', '39', '计算机', '212', 'static/images/book/22910975-1_w.jpg', null, 'Eric Sarrion');
INSERT INTO `books` VALUES ('C23E6E8A6DB94E27B6E2ABD39DC21AF5', 'JavaScript:The Good Parts(影印版）', '28', '少儿', '153', 'static/images/book/20412979-1_w.jpg', null, '克罗克福特');
INSERT INTO `books` VALUES ('C3CF52B3ED2D4187A16754551488D733', 'Java从入门到精通（附光盘）', '59', '励志', '519', 'static/images/book/20810282-1_w_1.jpg', null, '魔乐科技');
INSERT INTO `books` VALUES ('C86D3F6FACB449BEBD940D9307ED4A47', '编写高质量代码', '59', '生活', '303', 'static/images/book/22579686-1_w.jpg', '改善Java程序的151个建议(从语法、程序设计和架构、工具和框架、编码风格、编程思想5个方面探讨编写高质量Java代码的技巧、禁忌和最佳实践)', '秦小波');
INSERT INTO `books` VALUES ('CB0AB3654945411EA69F368D0EA91A00', 'JavaScript语言精粹（修订版）', '49', '生活', '155', 'static/images/book/22872884-1_w.jpg', null, '道格拉斯·克罗克福德');
INSERT INTO `books` VALUES ('CD913617EE964D0DBAF20C60076D32FB', '名师讲坛——', '79.8', '艺术', '831', 'static/images/book/20637368-1_w_2.jpg', 'Java开发实战经典（配光盘）（60小时全真课堂培训，视频超级给力！790项实例及分析，北京魔乐科技培训中心Java全部精华）', '李兴华');
INSERT INTO `books` VALUES ('CE01F15D435A4C51B0AD8202A318DCA7', 'Java编程思想（第4版）', '108', '考试', '880', 'static/images/book/9317290-1_w.jpg', null, '布鲁斯.艾克尔');
INSERT INTO `books` VALUES ('CF5546769F2842DABB2EF7A00D51F255', 'jQuery开发从入门到精通', '79.8', '学术', '619', 'static/images/book/23263012-1_w.jpg', '（配套视频327节，中小实例232个，实战案例7个商品详情手册11部，网页模版83类）（附1DVD）', '袁江');
INSERT INTO `books` VALUES ('D0DA36CEE42549FFB299B7C7129761D5', 'Java应用架构设计', '69', '经营', '251', 'static/images/book/23339643-1_w.jpg', '模块化模式与OSGi(全球资深Java技术专家的力作，系统、全面地讲解如何将模块化设计思想引入开发中，涵盖18个有助于实现模块化软件架构的模式)', '克内恩席尔德');
INSERT INTO `books` VALUES ('D0E69F85ACAB4C15BB40966E5AA545F1', 'Java并发编程实战', '69', '计算机', '290', 'static/images/book/22606835-1_w.jpg', '（第16届Jolt大奖提名图书，Java并发编程必读佳作）', 'Brian Goetz');
INSERT INTO `books` VALUES ('D2ABA8B06C524632846F27C34568F3CE', 'Java 经典实例', '98', '计算机', '784', 'static/images/book/20500255-1_w.jpg', null, '达尔文');
INSERT INTO `books` VALUES ('D8723405BA054C13B52357B8F6AEEC24', '深入理解Java虚拟机', '79', '计算机', '433', 'static/images/book/23259731-1_w.jpg', '：JVM高级特性与最佳实践（第2版）', '周志明');
INSERT INTO `books` VALUES ('DC36FD53A1514312A0A9ADD53A583886', 'JavaScript异步编程：', '32', '原版', '118', 'static/images/book/23252196-1_w.jpg', '设计快速响应的网络应用【掌握JavaScript异步编程必杀技，让代码更具响应度！ 】', 'Trevor Burnham ');
INSERT INTO `books` VALUES ('DCB64DF0084E486EBF173F729A3A630A', 'Java设计模式(第2版)', '75', '科技', '0', 'static/images/book/22868759-1_w.jpg', null, 'Steven John Metsker');
INSERT INTO `books` VALUES ('DEE7BDC7E0E343149E3C3601D2658171', '疯狂HTML 5/CSS 3/JavaScript讲义(含CD光盘1张)', '69', '小说', '500', 'static/images/book/22783904-1_w.jpg', null, '李刚');
INSERT INTO `books` VALUES ('DF4E74EEE89B43229BB8212F0B858C38', '精通Hibernate', '75', '计算机', '695', 'static/images/book/20773347-1_w_1.jpg', null, '孙卫琴');
INSERT INTO `books` VALUES ('E4F184188C8B4C7BB32D4E76603426AC', '疯狂Java讲义（第2版，附光盘）', '109', '励志', '844', 'static/images/book/22588603-1_w.jpg', null, '李刚');
INSERT INTO `books` VALUES ('EA695342393C4BE48B831FA5E6B0E5C4', '编写可维护的JavaScript', '55', '生活百科', '227', 'static/images/book/23200995-1_w.jpg', '《JavaScript高级程序设计》作者Nicholas Zakas最新力作，构建编码风格手册，帮助开发团队从“游击队”走向“正规军”）', 'Nicholas C. Zakas');
INSERT INTO `books` VALUES ('F0E34313BF304CCEBF198BD4E05307B8', 'jQuery Cookbook中文版（jQuery之父鼎力推荐，社区数十位专家倾情力作', '69', '计算机', '425', 'static/images/book/23219358-1_w.jpg', null, 'jQuery社区专家组');
INSERT INTO `books` VALUES ('F6162799E913423EA5CB57BEC65AB1E9', 'JUnit实战(第2版)', '79', '计算机', '442', 'static/images/book/22633574-1_w.jpg', null, '塔凯文');
INSERT INTO `books` VALUES ('F693239BC3B3444C8538ABE7411BB38E', 'Java Web典型模块与项目实战大全（配光盘）', '99.5', '励志', '922', 'static/images/book/20988080-1_w_1.jpg', null, '常建功');
INSERT INTO `books` VALUES ('F78C94641DB4475BBA1E72A07DF9B3AE', 'JAVA面向对象编程', '65.8', '生活', '625', 'static/images/book/9186890-1_w.jpg', null, '孙卫琴 ');
INSERT INTO `books` VALUES ('FC232CD9B6E6411BBBB1A5B781D2C3C9', 'Java与模式', '88', '生活', '1024', 'static/images/book/696673-1_w.jpg', null, '阎宏');
INSERT INTO `books` VALUES ('FEC3740CF30E442A94021911A25EF0D7', 'Spring攻略(第2版)(Spring攻略(第2版))', '128', '艺术', '938', 'static/images/book/22623020-1_w.jpg', null, 'Gary Mak　Josh Long　Daniel Rubio');

-- ----------------------------
-- Table structure for managers
-- ----------------------------
DROP TABLE IF EXISTS `managers`;
CREATE TABLE `managers` (
  `id` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  `pwd` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`,`name`)
) ENGINE=MyISAM DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of managers
-- ----------------------------
INSERT INTO `managers` VALUES ('a1', 'laoyao', '123456');

-- ----------------------------
-- Table structure for orderitem
-- ----------------------------
DROP TABLE IF EXISTS `orderitem`;
CREATE TABLE `orderitem` (
  `id` varchar(100) NOT NULL,
  `orderID` varchar(100) NOT NULL,
  `bookID` varchar(100) NOT NULL,
  `bookNum` int(11) NOT NULL,
  `bookName` varchar(100) NOT NULL,
  `bookPrice` double(50,0) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=32 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of orderitem
-- ----------------------------
INSERT INTO `orderitem` VALUES ('d2152e46-a28f-4f62-a370-d6007d73293e', 'df87dbe3-e291-4157-ae94-7d6d16bbbabc', '260F8A3594F741C1B0EB69616F65045B', '2', 'Tomcat与Java Web开发技术详解（第2版）(含光盘1张)', '79');
INSERT INTO `orderitem` VALUES ('bc997500-4b24-4c7b-a294-ab92a8a915b4', '256f6e40-e784-4fac-b91b-2a0bd17db125', '8740bee0-bfb8-4ba1-8f6e-b69310617db9', '2', '大勇和小花的欧洲日记', '26');
INSERT INTO `orderitem` VALUES ('da566d6c-a4b1-44f3-b47a-87298944b36c', 'f54bd841-3705-440d-bbec-44111d1429e4', '7C0C785FFBEC4DEC802FA36E8B0BC87E', '2', '深入分析Java Web技术内幕', '69');
INSERT INTO `orderitem` VALUES ('ba1518e0-dded-4078-b8c3-0bd9728878f1', '61d0f98f-ed13-43ac-a395-ca434cb02453', '000A18FDB38F470DBE9CD0972BADB23F', '1', 'Java Web整合开发实战--基于Struts 2+Hibernate+Spring', '80');
INSERT INTO `orderitem` VALUES ('99be5d27-42cb-4909-b5e5-94384234d8b7', '88327e13-42e6-474f-8279-1e3ad822dcba', '26ddd0c6-d7f2-4a83-b01b-286bb8c38420', '2', '.net设计规范', '50');
INSERT INTO `orderitem` VALUES ('305cd379-a5d2-40e3-a649-2c4284e5b37e', '957d77d7-9302-47e7-87bf-7f87aa7e3163', '2EE1A20A6AF742E387E18619D7E3BB94', '1', 'Java虚拟机并发编程', '59');
INSERT INTO `orderitem` VALUES ('57cc4e60-bab7-4df7-986c-4f7b302c319f', '957d77d7-9302-47e7-87bf-7f87aa7e3163', '113D0D1BB9174DD19A7DE7E2B37F677F', '2', 'Java7入门经', '118');
INSERT INTO `orderitem` VALUES ('c6839e64-fe74-4844-ac17-d51b64d892fc', 'f54bd841-3705-440d-bbec-44111d1429e4', '7D7FE81293124793BDB2C6FF1F1C943D', '3', '21天学通Java(第6版)（中文版累计销量超30000册）', '55');
INSERT INTO `orderitem` VALUES ('231ff64a-5847-4857-b5cb-a17e867e77cf', 'dd9edbe9-7351-494b-9754-3e03deb7ed9a', '000A18FDB38F470DBE9CD0972BADB23F', '1', 'Java Web整合开发实战--基于Struts 2+Hibernate+Spring', '80');

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `id` varchar(100) CHARACTER SET utf8 NOT NULL,
  `userID` varchar(50) NOT NULL,
  `totalPrice` double(50,0) NOT NULL,
  `orderDate` varchar(100) CHARACTER SET utf8 NOT NULL,
  `orderName` varchar(100) NOT NULL,
  `telephone` varchar(100) NOT NULL,
  `address` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=21 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES ('df87dbe3-e291-4157-ae94-7d6d16bbbabc', '5eb1bdad-b976-4e9d-9e06-eaf429943ccb', '159', '2019-04-07 02:49:33', '程雪', '19970043089', '江西省上饶市');
INSERT INTO `orders` VALUES ('f54bd841-3705-440d-bbec-44111d1429e4', '5eb1bdad-b976-4e9d-9e06-eaf429943ccb', '303', '2019-04-07 02:55:28', '吴思成乐', '18279953333', '广东省东莞市虎门镇玛莎公寓701');
INSERT INTO `orders` VALUES ('88327e13-42e6-474f-8279-1e3ad822dcba', '7834b175-2b67-4805-8235-55ead4452321', '100', '2019-04-06 02:27:02', '赵琴', '18770040115', '北京市丰台区云岗南区东里10栋');
INSERT INTO `orders` VALUES ('61d0f98f-ed13-43ac-a395-ca434cb02453', '5eb1bdad-b976-4e9d-9e06-eaf429943ccb', '80', '2019-04-06 09:35:13', '谭琪', '13979984297', '江西省萍乡市硖石新村朝阳花园');
INSERT INTO `orders` VALUES ('957d77d7-9302-47e7-87bf-7f87aa7e3163', '7834b175-2b67-4805-8235-55ead4452321', '295', '2019-04-06 02:29:09', '刘踞正', '18279948890', '江西省南昌市青山湖区蛟桥镇江西农业大学南区13栋501');
INSERT INTO `orders` VALUES ('256f6e40-e784-4fac-b91b-2a0bd17db125', '5eb1bdad-b976-4e9d-9e06-eaf429943ccb', '52', '2019-04-09 10:19:00', '李四', '15007992569', '江西省南昌市青山湖区江西农业大学南区');
INSERT INTO `orders` VALUES ('dd9edbe9-7351-494b-9754-3e03deb7ed9a', '6fc2d3fc-c75f-4deb-bd3d-46c1cc06062e', '80', '2019-04-10 09:52:33', '可乐', '18879940215', '江西省萍乡市朝阳花园');

-- ----------------------------
-- Table structure for products
-- ----------------------------
DROP TABLE IF EXISTS `products`;
CREATE TABLE `products` (
  `id` varchar(100) CHARACTER SET utf8 NOT NULL,
  `name` varchar(200) CHARACTER SET utf8 DEFAULT NULL,
  `price` double DEFAULT NULL,
  `nums` int(100) DEFAULT '1000',
  `type` char(50) DEFAULT NULL,
  `imgurl` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of products
-- ----------------------------
INSERT INTO `products` VALUES ('26ddd0c6-d7f2-4a83-b01b-286bb8c38420', '法国德菲丝松露精品巧克力500g/盒', '108', '1000', '今日特价', 'static/images/product/1.jpg');
INSERT INTO `products` VALUES ('3a0196b2-71c1-4d4d-a4e8-d1f875096f00', '乐扣普通型保鲜盒圣诞7件套', '69.9', '1000', '今日特价', 'static/images/product/2.jpg');
INSERT INTO `products` VALUES ('3cdd01d2-95d4-4077-b512-ff4c3b340d6b', '欧珀莱均衡保湿四件套', '279', '1000', '今日特价', 'static/images/product/3.jpg');
INSERT INTO `products` VALUES ('4dbac6bb-ac2a-4ea0-a62f-ea5a2fc2b3c2', '联想笔记本电脑 高速独立显存', '4199', '1000', '今日特价', 'static/images/product/4.jpg');
INSERT INTO `products` VALUES ('67a44950-935a-4dac-953d-515fd92d8174', '法姿韩版显瘦彩边时尚牛仔铅笔裤', '49', '1000', '今日特价', 'static/images/product/5.jpg');
INSERT INTO `products` VALUES ('72c52302-cd1e-4a22-8ac8-dc300a915be5', 'Genius925纯银施华洛世奇水晶吊坠', '69.9', '1000', '今日特价', 'static/images/product/6.jpg');
INSERT INTO `products` VALUES ('79bbe618-d2f8-4081-b35a-62ebbe938b64', '利仁2018M福满堂电饼铛 好用实惠', '268', '1000', '今日特价', 'static/images/product/7.jpg');
INSERT INTO `products` VALUES ('84c842da-16b6-4e87-953e-859a1ca62bab', '达派高档拉杆箱20寸 经典款式', '198', '1000', '今日特价', 'static/images/product/8.jpg');
INSERT INTO `products` VALUES ('8740bee0-bfb8-4ba1-8f6e-b69310617db9', '爱国者MP4 全屏触摸多格式播放', '289', '1000', '', 'static/images/product/9.jpg');
INSERT INTO `products` VALUES ('880fdb00-6798-4302-962b-f337f3393802', '多美滋金装金盾3阶段幼儿配方奶粉', '186', '1000', '', 'static/images/product/10.jpg');
INSERT INTO `products` VALUES ('968df3a0-be4d-473e-85a8-d3466b4ca7c2', '别做正常的傻瓜', '35', '1000', '热卖', 'static/images/book/792116e7-6d83-4be4-b3e5-4dd11b0b4565.png');
INSERT INTO `products` VALUES ('bdb32537-8f2c-4ba2-a752-94fdc0e9a250', '经济案例解析', '56.5', '1000', '热卖', 'static/images/book/8efe720f-fa72-435b-a3c3-69230f9677cc.png');
INSERT INTO `products` VALUES ('DCB64DF0084E486EBF173F729A3A630A', '法国德菲丝松露精品巧克力500g/盒', '108', '1000', '热卖', 'static/images/product/1.jpg');
INSERT INTO `products` VALUES ('DEE7BDC7E0E343149E3C3601D2658171', '乐扣普通型保鲜盒圣诞7件套', '69.9', '1000', '热卖', 'static/images/product/2.jpg');
INSERT INTO `products` VALUES ('DF4E74EEE89B43229BB8212F0B858C38', '欧珀莱均衡保湿四件套', '279', '1000', '热卖', 'static/images/product/3.jpg');
INSERT INTO `products` VALUES ('E4F184188C8B4C7BB32D4E76603426AC', '联想笔记本电脑 高速独立显存', '4199', '1000', '热卖', 'static/images/product/4.jpg');
INSERT INTO `products` VALUES ('EA695342393C4BE48B831FA5E6B0E5C4', '法姿韩版显瘦彩边时尚牛仔铅笔裤', '49', '1000', '热卖', 'static/images/product/5.jpg');
INSERT INTO `products` VALUES ('F0E34313BF304CCEBF198BD4E05307B8', 'Genius925纯银施华洛世奇水晶吊坠', '69.9', '1000', '热卖', 'static/images/product/6.jpg');
INSERT INTO `products` VALUES ('F6162799E913423EA5CB57BEC65AB1E9', '利仁2018M福满堂电饼铛 好用实惠', '268', '1000', '热卖', 'static/images/product/7.jpg');
INSERT INTO `products` VALUES ('F693239BC3B3444C8538ABE7411BB38E', '达派高档拉杆箱20寸 经典款式', '198', '1000', '热卖', 'static/images/product/8.jpg');
INSERT INTO `products` VALUES ('F78C94641DB4475BBA1E72A07DF9B3AE', '爱国者MP4 全屏触摸多格式播放', '289', '1000', '热卖', 'static/images/product/9.jpg');
INSERT INTO `products` VALUES ('FC232CD9B6E6411BBBB1A5B781D2C3C9', '多美滋金装金盾3阶段幼儿配方奶粉', '186', '1000', '热卖', 'static/images/product/10.jpg');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  `pwd` varchar(50) NOT NULL,
  `email` varchar(100) NOT NULL,
  `tel` varchar(20) NOT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '0',
  `activationCode` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`,`tel`,`name`,`email`)
) ENGINE=MyISAM AUTO_INCREMENT=132 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('5eb1bdad-b976-4e9d-9e06-eaf429943ccb', 'tanqi', 'tanqi', 'tan_qi@sohu.com', '13979984297', '1', '');
INSERT INTO `users` VALUES ('7834b175-2b67-4805-8235-55ead4452321', 'caddo', 'caddo', '2108163365@qq.com', '19970043087', '1', '');
INSERT INTO `users` VALUES ('923ed6ce-969c-49a9-addb-aa7fd14d6118', 'qeqe', 'qqqq', '1205265301@qq.com', '15979204426', '0', '923ed6ce-969c-49a9-addb-aa7fd14d6118');
INSERT INTO `users` VALUES ('6fc2d3fc-c75f-4deb-bd3d-46c1cc06062e', 'kele', 'kele', '1121959155@qq.com', '18770040115', '1', null);

create database OnlineQuiz
create table dbo.tblRole (
	roleID varchar(2),
	name varchar(10),
	detail varchar(500),
	CONSTRAINT PK_tblRole PRIMARY KEY (roleID));

insert into dbo.tblRole(roleID, name) values 
('AD', 'admin'),('S', 'Student'),('S1', 'SubStudent'),('T', 'Teacher'),('T1', 'SubTeacher'),('U', 'Unverified');
insert into dbo.tblRole(roleID, name) values ('U', 'Unabled');
create table dbo.tblUser (
	userID varchar(250),
	[password] varchar (250) not null,
	fullName varchar (250),
	roleID varchar(2)
	foreign key(roleID) references  dbo.tblRole(roleID),
	phone int, 
	email varchar(250),
	[address] varchar(250)
	CONSTRAINT PK_tblUser PRIMARY KEY (userID)
);
alter table dbo.tblUser add verification_code varchar(64);
alter table dbo.tblUser add [image] varchar(8000);
alter table dbo.tblUser add CONSTRAINT PK_tblUser PRIMARY KEY (email)


insert into dbo.tblUser (userID, [password], fullName, roleID, email, [address])
values 
('kevin', 111111, 'Kevin De Bruyne', 'G', 'kevin@mancity.com', 'Belgium'),
('ndungx', 123456, 'Nguyen Dung', 'G', 'ndungx@gmail.com', 'Viet Nam'),
('james', 123456, 'Nguyen Tuan Khai', 'AD', 'tuankhai513@gmail.com','Viet Nam'),
('roberto', 123456, 'Roberto Firmino', 'G', 'Firmino@liverpool.com', 'Brazil'),
('servlet', 123456, 'Servlet Tomcat Server', 'G', 'Servlet@Tomcat.com', 'Local host');

--------


create table dbo.tblCategory (
	categoryID int primary key identity,
	categoryName varchar (250),
	description varchar(250),
	level varchar(50),
	status bit
);


create table dbo.tblCourse (
	CourseID int primary key identity,
	AuthorID varchar(250) foreign key references dbo.tblUser(userID),
	Duration varchar(10),
	Status bit);

	alter table dbo.tblCourse add categoryID int foreign key references dbo.tblCategory(categoryID);	
	alter table dbo.tblCourse add Name varchar(250);	
	alter table dbo.tblCourse add Description varchar(MAX);	

	
create table dbo.tblClass (
	ClassID int primary key identity,
	NumberOfStudent int,
	Status varchar(50));
	


create table dbo.tblLecture (
	LectureID int primary key identity,
	LectureName varchar(250),
	Description varchar(MAX),
	CourseID int foreign key references dbo.tblCourse(courseID),
	ClassID int foreign key references dbo.tblClass(classID),
	Status varchar(50));

create table dbo.tblSource (
	SourceID int primary key identity,
	LectureID int foreign key references dbo.tblLecture(lectureID),
	FileDoc varchar(250),
	FilePic varchar(250),
	FileVid varchar(250),
	Reference varchar(250),
	Status varchar(50));

create table dbo.tblCourseDetail (
	CourseID int,
	LearnerID varchar(250),
	StartDate datetime,
	Progress float,
	ClassID int,
	foreign key (CourseID) references dbo.[tblCourse](CourseID),
	foreign key (LearnerID) references dbo.tblUser(UserID),	
	foreign key (ClassID) references dbo.tblClass(ClassID),
	primary key(CourseID, LearnerID)
);

create table dbo.tblCategoryBlog (
	categoryID int primary key identity,
	categoryName varchar (250),
	description varchar(250),
	status varchar(50)
);

create table dbo.tblBlog (
	BlogID int primary key identity,
	Title varchar (250),
	Content text,
	Image text,
    categoryID int foreign key references dbo.tblCategoryBlog(categoryID),
	AuthorID varchar(250) foreign key references dbo.tblUser(UserID)
);


create table dbo.tblService (
	ServiceID int primary key identity,
	Name varchar(250),
	Description text,
	Price float,
	Status varchar(50));

create table dbo.tblBilling (
	BillingID  int primary key identity,
	userID varchar(250) foreign key references dbo.tblUser(userID),
	[Date] datetime,	
	serviceID int foreign key references dbo.tblService(serviceID),
	PaymentType varchar(50),
	status varchar(50),
);

create table dbo.tblQuiz (
	QuizID int primary key identity,
	Name varchar(250),
	NumberOfQuestions int,
	Description text,
	TotalMark float,
	AuthorID varchar(250) foreign key references dbo.tblUser(UserID),
	Status varchar(50));

	alter table dbo.tblQuiz add ClassID int foreign key references dbo.tblClass(ClassID);	

	create table dbo.tblQuestion (
	QuestionID int primary key identity,
	Name varchar(250),
	Question1 text,
	Question2 text,
	Question3 text,
	Question4 text,
	Description text,
	Answer int,
	AuthorID varchar(250) foreign key references dbo.tblUser(UserID),
	Status varchar(50));

	
	alter table dbo.tblQuestion add categoryID int foreign key references dbo.tblCategory(categoryID);	

	create table dbo.tblQuizDetail (
	QuizID int,
	QuestionID int,
	Time varchar(250),
	Mark float,
	foreign key (QuizID) references dbo.[tblQuiz](QuizID),
	foreign key (QuestionID) references dbo.tblQuestion(questionID),	
	primary key(quizID, questionID)
);


	create table dbo.tblScore (
	QuizID int,
	UserID varchar(250),
	StartTime datetime,
	EndTime datetime,
	Mark float,
	foreign key (QuizID) references dbo.[tblQuiz](QuizID),
	foreign key (userID) references dbo.tbluser(userID),	
	primary key(quizID, userID)
);



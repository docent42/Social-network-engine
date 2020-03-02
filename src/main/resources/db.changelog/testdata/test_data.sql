
BEGIN;

INSERT INTO notification_type(id, code, name) VALUES
(1, -1404242890, 'FRIEND_REQUEST'),
(2, 110, 'POST_COMMENT'),
(3, -100423240, 'FRIEND_REQUEST'),
(4, -1869329863, 'MESSAGE'),
(5, -18708949, 'MESSAGE'),
(6, -1962472351, 'COMMENT_COMMENT'),
(7, -7, 'FRIEND_REQUEST'),
(8, -169479288, 'COMMENT_COMMENT'),
(9, -150832482, 'POST'),
(10, -8355285, 'COMMENT_COMMENT');

INSERT INTO person(id, first_name, last_name, reg_date, birth_date, e_mail, phone, password, photo, about, town, confirmation_code, is_approved, messages_permission, last_online_time, is_blocked) VALUES
(1, 'Bonita', 'Boyer', '2016-01-06', '1972-10-07', 'tarakan@mail.ru', 68350669, '$2y$12$hxinuV95vDxAV.443EqZv.kFp2Jr9pcnOwRAkEZQGIrIQLvfI8Qsa', 'http://www.gravatar.com/avatar/6ffc4796e63ba040e769b8b9d150107?s=40&d=http%3A%2F%2Fwww.example.com%2Fdefault.jpg', 'Looking it another way, a lot of effort has been invested into the flexible production planning.', 'Zenon Park', 'ec44e829-7203-4e76-86c-3672981f8470', False, 'ALL', '1971-01-01 01:18:15', False),
(2, 'Ernie', 'Adamson', '2014-09-30', '195-02-22', 'WaltraudK.Pearson96@example.com', 44968843, 'qJVXwGJsAXf+hpw63pr0XA==', 'http://www.gravatar.com/avatar/6ffc4796e63ba040e769b8b9d150107?s=40&d=http%3A%2F%2Fwww.example.com%2Fdefault.jpg', 'For instance, the exceptional results of the entity integrity should focus on the critical thinking.', 'Ste-Rosalie', '1c7cc782-9ae5-411c-8fef-6b769d3b7f0', False, 'FRIENDS', '1971-02-01 13:14:3', False),
(3, 'Hosea', 'Tolliver', '2000-02-16', '1988-03-12', 'dydqi814@example.com', 982937485, 'iz7oNx0wR83aAyU1ZanHMg==', 'http://www.gravatar.com/avatar/6ffc4796e63ba040e769b8b9d150107?s=40&d=http%3A%2F%2Fwww.example.com%2Fdefault.jpg', 'Doubtless, the predictable behavior becomes a serious problem.', 'Verdun', '38375f5-6635-4887-90c1-e00708cd7290', False, 'FRIENDS', '1971-01-01 00:04:36', False),
(4, 'Maryanna', 'Boykin', '1996-04-22', '1981-09-07', 'ElliottSkaggs@example.com', 989219271, 'B49cPNanNXRYLqi4xq5jUg==', 'http://www.gravatar.com/avatar/6ffc4796e63ba040e769b8b9d150107?s=40&d=http%3A%2F%2Fwww.example.com%2Fdefault.jpg', 'On top of that a description of the essence may motivate developers to work out the driving factor.', 'Ste-Rose-de-Watford', '1e83f0d-d7e2-4abb-93ba-3cf9c87a406', False, 'FRIENDS', '1983-08-03 00:3:27', False),
(5, 'Hank', 'Mullin', '1970-01-04', '198-10-10', 'HerschelKendall72@example.com', 80403776, 'tyVVd/mlhqpmA9bLgOboqw==', 'http://www.gravatar.com/avatar/6ffc4796e63ba040e769b8b9d150107?s=40&d=http%3A%2F%2Fwww.example.com%2Fdefault.jpg', 'In the meantime the structure of the set of related commands and controls the product functionality.', 'Puce', 'db77dbf-7ab9-44fb-bbb5-c0c6b10fd67', False, 'FRIENDS', '1971-01-01 00:08:00', False),
(6, 'Alpha', 'Figueroa', '2015-07-21', '1974-06-26', 'SheriseLogue@example.com', 330900623, '2LCB05kQfEA3e0xgEoF4cw==', 'http://www.gravatar.com/avatar/6ffc4796e63ba040e769b8b9d150107?s=40&d=http%3A%2F%2Fwww.example.com%2Fdefault.jpg', 'Besides, the pursuance of well-known practice becomes a serious problem.', 'DeBolt', '98f8121-deb2-43f4-af6c-3ad210fbfe0', False, 'ALL', '1971-01-01 00:09:03', False),
(7, 'Elisha', 'Schmitz', '1988-03-03', '1994-12-05', 'Melda.Mercier27@nowhere.com', 9433394, 'aR4E8mb1HoXwwBr30wPzeA==', 'http://www.gravatar.com/avatar/6ffc4796e63ba040e769b8b9d150107?s=40&d=http%3A%2F%2Fwww.example.com%2Fdefault.jpg', 'Up to a certain time, the development of the specific action result is closely considerable.', 'Beaumont', 'ef4c2363-f5a6-4610-a02b-43cc12e75188', True, 'FRIENDS', '1971-01-01 00:13:03', False),
(8, 'Clifford', 'Tomlin', '1972-06-22', '193-04-09', 'FossD3@nowhere.com', 8272165, 'QJZawv790ovKg8PJ20FIKA==', 'http://www.gravatar.com/avatar/6ffc4796e63ba040e769b8b9d150107?s=40&d=http%3A%2F%2Fwww.example.com%2Fdefault.jpg', 'Notwithstanding that any big impact gives us a clear notion of the conceptual design.  ', 'Glen Water', 'c0f07f7a-cda3-4ed3-a6b9-204633adede2', True, 'FRIENDS', '1974-10-28 16:3:3', False),
(9, 'Kenneth', 'Rayburn', '2009-03-09', '1950-01-10', 'Copeland@example.com', 283044892, 'wsaOBuMoSerunSpSKt9YEg==', 'http://www.gravatar.com/avatar/6ffc4796e63ba040e769b8b9d150107?s=40&d=http%3A%2F%2Fwww.example.com%2Fdefault.jpg', 'All in all, the explicit examination of final phase manages to obtain the major outcomes.', 'Vermilion', 'ac19121b-4cfa-4d2-b6f6-17a0f271b64', False, 'ALL', '2007-03-19 20:15:17', False),
(10, 'Dominique', 'Schneider', '1987-07-09', '1950-04-11', 'Meacham33@nowhere.com', 8223815, 'GJB695A/aOWq5VGsUHmj6A==', 'http://www.gravatar.com/avatar/6ffc4796e63ba040e769b8b9d150107?s=40&d=http%3A%2F%2Fwww.example.com%2Fdefault.jpg', 'As concerns support of the optimization scenario, it can be quite risky.', 'Ste-Rose-du-Nord', 'ef4b1b32-b943-4104-813c-ec8e776dab12', False, 'FRIENDS', '1971-01-01 00:00:30', False);

INSERT INTO post(id, time, author_id, title, post_text, is_blocked, is_deleted) VALUES
(1, '1971-01-01 00:28:11', 9, 'Non nostrum dignissimos.', 'Focusing on the latest investigations, we can positively say that the example of the feedback system must stay true to an importance of the well-known practice.  ', False, False),
(2, '1981-11-10 03:2:13', 5, 'Doloremque qui rerum.', 'The most common argument against this is that the initial progress in the specific decisions reinforces the argument for any contemporary or prominent approach.  ', False, False),
(3, '1983-12-09 07:15:10', 8, 'Nulla fuga exercitationem.', 'To straighten it out, the conventional notion of the arrangement of the system mechanism provides rich insights into the predictable behavior.  ', True, False),
(4, '1993-07-13 07:19:30', 1, 'Ad voluptate laudantium.', 'One cannot deny that a number of brand new approaches has been tested during the the improvement of the final draft.', True, False),
(5, '1971-01-01 00:15:34', 7, 'Numquam et eveniet.', 'In respect that any technical requirements impacts fully on every grand strategy. In respect of the condition of the flexible production planning may share attitudes on the potential role models.', False, True),
(6, '1971-01-01 00:00:05', 7, 'Qui inventore est.', 'Besides, components of an overview of the strategic planning poses problems and challenges for both the internal network and the basic reason of the closely developed techniques.', True, False),
(7, '1971-01-01 00:00:02', 7, 'Veniam voluptas est.', 'On the contrary, the accurate predictions of the sources and influences of the overall scores may share attitudes on the benefits of data integrity. Thus a complete understanding is missing.', True, True),
(8, '1990-09-01 13:25:49', 7, 'Rerum eos quam.', 'As for some of the feedback system, it is clear that the initial progress in the base configuration gives rise to the entire picture.', False, True),
(9, '2003-04-05 03:35:01', 1, 'Sed quidem cupiditate.', 'The the layout of the commitment to quality assurance gives less satisfactory results.', True, False),
(10, '1977-02-20 02:00:16', 8, 'Nihil quos aspernatur.', 'Nevertheless, one should accept that the big impact provides a glimpse at the strategic planning. Thus a complete understanding is missing.  ', False, False);

INSERT INTO post_comment(id, time, post_id, parent_id, author_id, comment_text, is_blocked, is_deleted) VALUES
(1, '1971-01-01 00:00:07', 9, null, 10, 'By the way, with help of the skills leads us to a clear understanding of the conceptual design.  ', False, True),
(2, '2006-11-23 05:4:15', 9, 1, 4, 'In a word, each of the task analysis impacts typically on every market tendencies.', True, True),
(3, '2006-12-23 23:20:32', 9, 2, 8, 'What is more, the condition of the treatment provides a strict control over the sufficient amount.', True, False),
(4, '1974-05-31 10:02:51', 6, null, 7, 'By the way, any further consideration cannot be developed under such circumstances.', False, False),
(5, '1971-01-01 00:00:08', 6, 4, 1, '1The the framework of the primary element gives less satisfactory results.', True, False),
(6, '1971-01-01 00:00:08', 6, 5, 2, '2e the framework of the primary element gives less satisfactory results.', True, False),
(7, '1971-01-01 00:00:08', 4, null, 3, 'T3he the framework of the primary element gives less satisfactory results.', True, False),
(8, '1971-01-01 00:00:08', 4, 7, 5, '4The the framework of the primary element gives less satisfactory results.', True, False),
(9, '1971-01-01 00:00:08', 4, 7, 6, 'T5he the framework of the primary element gives less satisfactory results.', True, False),
(10, '1971-01-01 00:00:08', 4, 9, 9, 'T6he the framework of the primary element gives less satisfactory results.', True, False);

INSERT INTO post_like(id, time, person_id, post_id) VALUES
(1, '1971-01-01 01:20:00', 5, 5),
(2, '2016-07-01 15:33:11', 7, 7),
(3, '1971-01-01 00:00:23', 9, 9),
(4, '1971-01-01 00:00:11', 9, 9),
(5, '2000-09-22 13:08:01', 3, 3),
(6, '1971-01-01 00:00:05', 3, 3),
(7, '1983-09-01 16:45:08', 1, 1),
(8, '2012-11-29 09:02:27', 1, 1),
(9, '1971-01-01 01:29:01', 8, 8),
(10, '1971-01-01 00:00:01', 10, 10);

INSERT INTO tag(id, tag) VALUES
(1, 'онлайн кабинет'),
(2, 'телефоны играть смотреть скачать'),
(3, 'играть скачать телефон алиэкспресс девушки'),
(4, 'продажа фильмы скорость игры одежды'),
(5, 'tagName'),
(6, 'новинки игры'),
(7, 'свинка квартир скачать для суд тексты почта'),
(8, 'скачать база 6 отслеживание смотреть'),
(9, 'серии стиральные'),
(10, 'игры дневник онлайн на свинка с язык продажа');

INSERT INTO block_history(id, time, person_id, post_id, comment_id, action) VALUES
(1, '1971-01-01 00:14:02', 4, 4, 3, 'UNBLOCK'),
(2, '2018-01-15 19:15:07', 5, 5, 2, 'UNBLOCK'),
(3, '1995-10-02 07:44:20', 7, 7, 2, 'UNBLOCK'),
(4, '1989-03-01 09:23:24', 8, 8, 3, 'UNBLOCK'),
(5, '1971-01-01 02:12:6', 7, 7, 5, 'BLOCK'),
(6, '1978-12-21 17:47:17', 1, 1, 5, 'BLOCK'),
(7, '2016-09-23 00:6:07', 6, 6, 4, 'UNBLOCK'),
(8, '1981-04-05 02:32:42', 9, 9, 2, 'UNBLOCK'),
(9, '1971-02-07 14:6:26', 9, 9, 4, 'UNBLOCK'),
(10, '2001-09-23 00:2:51', 8, 8, 4, 'UNBLOCK');

INSERT INTO comment_like(id, time, person_id, comment_id) VALUES
(1, '1979-02-20 05:12:1', 8, 3),
(2, '1978-08-19 10:18:20', 7, 2),
(3, '1971-01-01 02:07:38', 2, 2),
(4, '1995-12-10 04:17:25', 8, 3),
(5, '1971-01-01 00:00:04', 5, 5),
(6, '1980-04-14 02:06:48', 10, 5),
(7, '2001-12-31 20:47:3', 9, 4),
(8, '1974-08-22 10:06:19', 7, 2),
(9, '1971-01-01 00:00:3', 9, 4),
(10, '1984-10-31 07:25:26', 4, 4);

INSERT INTO dialog(id, unread_count, owner_id, is_deleted, invite_code,last_message) VALUES
(1, 0, 1, false, '89WPYH816OE2J22478GY',1),
(2, 0, 10, false, '9NFM2A2RW86T3P106ZW',1),
(3, 0, 4, false, '208RH2465L00SSQ1H17E',1),
(4, 0, 3, false, '5124H26491810Y5VAM105',1),
(5, 0, 5, false, '78106I693EGGLV3867XH0',1),
(6, 0, 1, false, 'LU1TGE6M8984950TVI73B',1),
(7, 0, 8, false, 'X193PB3P36226300YR7HU',1),
(8, 0, 6, false, 'T35M398B9E08X0HMUL66A',1),
(9, 0, 10, false, 'X74BBMS91410VGUB0S2V',1),
(10, 0, 9, false, '6SDKMOH02X3G7JOQWEKJ',1);

INSERT INTO friendship_status(id, time, name, code) VALUES
(1, '1971-01-01 00:00:05', 'id1->id2', 'FRIEND'),
(2, '1981-10-09 04:48:34', 'id2->id1', 'FRIEND'),
(3, '2000-04-21 10:18:29', 'id1->id3', 'SUBSCRIBED'),
(4, '1971-01-01 01:30:4', 'id3->id1', 'REQUEST'),
(5, '1989-11-27 15:44:27', 'id1->id5', 'FRIEND'),
(6, '1985-11-16 01:20:14', 'id6->id1', 'REQUEST'),
(7, '2003-05-08 21:21:01', 'id1->id7', 'FRIEND'),
(8, '2000-05-19 23:08:29', 'id8->id1', 'FRIEND'),
(9, '2012-09-11 02:23:36', 'fs09', 'SUBSCRIBED'),
(10, '1971-01-01 00:00:07', 'fs10', 'DECLINED');

INSERT INTO friendship(id, status_id, src_person_id, dst_person_id) VALUES
(1, 1, 1, 1),
(2, 2, 1, 2),
(3, 3, 1, 3),
(4, 4, 3, 1),
(5, 5, 1, 5),
(6, 6, 1, 6),
(7, 7, 1, 7),
(8, 8, 1, 8),
(9, 7, 10, 10),
(10, 5, 10, 10);

INSERT INTO message(id, time, author_id, dialog_id, message_text, read_status, is_deleted) VALUES
(1, '1995-12-19 13:51:28', 8, 8, 'One should, however, not forget that the assumption, that the corporate asset growth is a base for developing the progress of the effective mechanism, should help in resolving present challenges. It is stated that with the exception of the internal resources makes it easy to see perspectives of the ultimate advantage of advanced proportion over alternate practices.  \r\nIt is stated that the initial progress in the systolic approach should help in resolving present challenges. It is undeniable that one of the mechanism represents a bond between the systems approach and the diverse sources of information. A solution might be in a combination of comprehensive set of policy statements and first-class package what is conventionally known as integration prospects.  ', 'READ',0),
(2, '2013-07-13 17:17:28', 7, 7, 'In particular, a section of the vital decisions is steadily considerable. However, an assessment of the design aspects highlights the importance of the general features and possibilities of the base configuration.  \r\nFortunately, either permanent growth or significant improvement gives a complete experience of the preliminary action plan.  \r\nCuriously, a key factor of the basic feature holistically illustrates the utter importance of the potential role models. It may reveal how the project architecture seamlessly the ability bias. Everyone understands what it takes to the predictable behavior. Thus a complete understanding is missing the irrelevance of property complete failure of the supposed theory.  \r\nAdmitting that some of the strategic decision cannot rely only on the structural comparison, based on sequence analysis. We must be ready for primary element and key factor investigation of what can be classified as the final phase. A solution might be in a combination of design patterns and crucial component any pragmatic or inclusive approach.  \r\nBy some means, the conventional notion of the interpretation of the project architecture should correlate with every contradiction between the valuable information and the crucial component.  \r\nBy the way, an basic component of details of the direct access to key resources indicates the importance of the entire picture.  \r\nIt goes without saying that final stages of the share of corporate responsibilities remains the crucial component of any standards control. This may be done through the software functionality.  \r\nNevertheless, one should accept that a section of the critical thinking impacts heavily on every grand strategy. In respect of the dominant cause of the functional programming has more common features with the irrelevance of assistance.  \r\nAlthough, criteria of the remainder of the development process  poses problems and challenges for both the basics of planning and scheduling and any meticulous or acceptable approach.  ', 'SENT',0),
(3, '1971-01-01 00:00:04', 2, 2, 'We must bear in mind that the basic layout for with help of the permanent growth must take into account the possibility of an importance of the technical terms.  \r\nTo sort everything out, it is worth mentioning that the possibility of achieving any technical requirements, as far as the user interface is questionable, provides a solid basis for the ability bias.  \r\nTo be more specific, in terms of the essence can hardly be compared with the irrelevance of project.  \r\nIn a more general sense, the condition of the skills boosts the growth of the preliminary network design or the effective mechanism.  \r\nHowever, we can also agree that an overview of the big impact approximately illustrates the utter importance of the vital decisions. This seems to be a particularly obvious step towards the change of marketing strategy.  \r\nSo far, the unification of the systems approach gives a complete experience of the general features and possibilities of the structural comparison, based on sequence analysis. A solution might be in a combination of well-known practice and formal review of opportunities any sole or experimental approach.  \r\nFor a number of reasons, the possibility of achieving the interpretation of the valuable information, as far as the direct access to key resources is questionable, is getting more complicated against the backdrop of the continuing record doctrine. In any case, we can heavily change the mechanism of the questionable thesis.  \r\nOne should, nevertheless, consider that the major accomplishments, such as the program functionality, the functional programming, the product functionality or the content strategy can hardly be compared with this design aspects. This can eventually cause certain issues.  \r\nOne way or another, the problem of the layout of the application rules what can be classified as the first-class package the sustainability of the project and this preliminary network design. This can eventually cause certain issues.  ', 'SENT',0),
(4, '1984-06-30 03:7:40', 8, 8, 'To sort everything out, it is worth mentioning that final stages of the critical thinking must be compatible with what can be classified as the standards control.  \r\nIn respect that the explicit examination of specific action result cannot rely only on the major decisions, that lie behind the functional testing. Such tendency may financially originate from the quality guidelines.  \r\nAs a matter of fact the accurate predictions of the software engineering concepts and practices should correlate with the general features and possibilities of the program functionality.  \r\nIn spite of the fact that the problem of the driving factor discards the principle of complete failure of the supposed theory. The main reason of the product design and development is to facilitate the positive influence of any technical requirements, it is worth considering that a number of brand new approaches has been tested during the the improvement of the base configuration. Let''s not forget that the portion of the basic feature becomes extremely important for the market tendencies. The primary element turns it into something habitually real.  \r\nBesides, the framework of the treatment poses problems and challenges for both the market tendencies and the specific decisions. Everyone understands what it takes to this fundamental problem. This can eventually cause certain issues the task analysis or the user interface.  \r\nIt is worth emphasizing that the initial progress in the continuing emergency doctrine the irrelevance of decision general tendency of the diverse sources of information. This could slightly be a result of a best practice patterns.  \r\nFirst and foremost, a number of brand new approaches has been tested during the the improvement of the predictable behavior. In particular, the problem of core concept of the market tendencies gives an overview of the questionable thesis.  ', 'READ',0),
(5, '2019-04-06 22:41:01', 5, 5, 'By the way, the conventional notion of any feedback system becomes even more complex when compared with the proper subject of the data management and data architecture framework.  \r\nQuite possibly, the pursuance of competitive development and manufacturing needs to be processed together with the the more increasing growth of technology and productivity of the system mechanism. A solution might be in a combination of program functionality and major area of expertise the proper innovation of the prominent landmarks.  \r\nOtherwise speaking, criteria of an assessment of the entity integrity needs to be processed together with the an importance of the program functionality.  \r\nConsequently, the problem of the ability bias has a long history of the minor details of global management concepts.  \r\nBy the way, the pursuance of systolic approach needs to be processed together with the the preliminary action plan.  \r\nOne of the most striking features of this problem is that criteria of the structure of the source of permanent growth decidedly the questionable thesis the application interface in terms of its dependence on an initial attempt in development of the draft analysis and prior decisions and early design solutions.  ', 'READ',0),
(6, '1971-01-01 00:07:05', 10, 10, 'To be quite frank, concentration of a key factor of the continuing support ensures integrity of the operating speed model. Everyone understands what it takes to any functional testing. This may be done through the strategic management an initial attempt in development of the application interface.  \r\nNotwithstanding that the pursuance of efficient decision provides a glimpse at complete failure of the supposed theory.  ', 'READ',0),
(7, '1971-01-01 00:16:00', 9, 9, 'Let it not be said that the pursuance of strategic decisions seems to typically change the paradigm of the final phase. This could seemingly be a result of a structured technology analysis.  \r\nAs a matter of fact the framework of the arguments and claims would facilitate the development of the continuing idea doctrine.  \r\nFortunately, dimensions of the network development can be regarded as particularly insignificant. The matters of peculiar interest is regularly debated in the light of the matters of peculiar interest. Such tendency may rationally originate from the valuable information.  \r\nEven so, all approaches to the creation of the efficiency of the performance gaps would facilitate the development of the ultimate advantage of explicit access over alternate practices.  \r\nAnother way of looking at this problem is to admit that the pursuance of formal review of opportunities the crucial component. We must be ready for systolic approach and critical thinking investigation of the ultimate advantage of original assessment over alternate practices the driving factor and the questionable thesis.  \r\nFor a number of reasons, a closer study of the permanent growth focuses our attention on the key factor. Thus a complete understanding is missing.  \r\nUp to a certain time, the total volume of the treatment cannot rely only on the basic reason of the system mechanism. Such tendency may differently originate from the final draft.  \r\nConsequently, the raw draft of the set of related commands and controls must be compatible with the irrelevance of skill.  ', 'READ',0),
(8, '2011-09-29 10:37:19', 7, 7, 'It is worth emphasizing that the remainder of the arguments and claims cannot rely only on the goals and objectives. This seems to be a easily obvious step towards the major outcomes.  \r\nLet''s not forget that the basic layout for the edge of the first-class package enforces the overall effect of what is conventionally known as systems approach.  \r\nOn the one hand it can be said that a broad understanding of the basic feature focuses our attention on the conceptual design.  \r\nIn a loose sense all approaches to the creation of general features of the content strategy poses problems and challenges for both the effective mechanism and the competitive development and manufacturing. The situation is quite a insignificant matter.  \r\nIn respect that a lot of effort has been invested into the source of permanent growth. Another way of looking at this problem is to admit that the formal review of opportunities in its influence on elements of the functional testing an importance of the presumably developed techniques any technical terms. This may be done through the participant evaluation sample.  \r\nAnother way of looking at this problem is to admit that all approaches to the creation of the progress of the fundamental problem accordingly changes the principles of every contradiction between the preliminary network design and the key factor.  \r\nFocusing on the latest investigations, we can positively say that the basic layout for elements of the storage area results in a complete compliance with the independent knowledge. Such tendency may entirely originate from the specific decisions.  \r\nThe most common argument against this is that an basic component of the dominant cause of the flexible production planning indicates the importance of the user interface. Such tendency may steadily originate from the increasing growth of technology and productivity.  ', 'READ', 0),
(9, '1971-01-01 00:11:41', 9, 9, 'One of the most striking features of this problem is that the explicit examination of sources and influences of the significant improvement cannot be developed under such circumstances. In the meantime a closer study of the individual elements becomes a serious problem. Though, the objectives of the remainder of the best practice patterns can be neglected in most cases, it should be realized that an assessment of the the profit the draft analysis and prior decisions and early design solutions. In any case, we can differently change the mechanism of what is conventionally known as critical acclaim of the the high performance of the proper architecture of the critical acclaim of the.  \r\nFurthermore, one should not forget that a closer study of the share of corporate responsibilities offers good prospects for improvement of the final draft. Such tendency may deeply originate from the goals and objectives.  \r\nOne of the most striking features of this problem is that the center of the comprehensive methods will require a vast knowledge. In a more general sense, the unification of the technical requirements provides a solid basis for the major outcomes. The real reason of the corporate competitiveness immensely the positive influence of any crucial development skills the diverse sources of information. We must be ready for well-known practice and share of corporate responsibilities investigation of the network development. Everyone understands what it takes to the sources and influences of the effective mechanism. Everyone understands what it takes to every contradiction between the operating speed model and the driving factor the permanent growth. This could rigorously be a result of a direct access to key resources the relational approach. Such tendency may ridiculously originate from the major outcomes.  \r\nOne should, nevertheless, consider that the example of the comprehensive set of policy statements results in a complete compliance with any high-power or full-featured approach.  ', 'READ', 0),
(10, '1971-01-01 00:15:46', 4, 4, 'Speaking about comparison of a description of the primary element and user interface, a deliberate action of a broad understanding of the effective mechanism enforces the overall effect of the more application rules of the comprehensive set of policy statements.  \r\nThus, any formal action manages to obtain this key principles. This can eventually cause certain issues.  ', 'SENT', 0),
(11, '1971-01-01 00:15:47', 1, 1, 'Speaking about comparison of a description of the primary element and user interface, a deliberate action of a broad understanding of the effective mechanism enforces the overall effect of the more application rules of the comprehensive set of policy statements.  \r\nThus, any formal action manages to obtain this key principles. This can eventually cause certain issues.  ', 'SENT', 0),
(12, '1971-01-01 00:15:48', 1, 3, 'Speaking about comparison of a description of the primary element and user interface, a deliberate action of a broad understanding of the effective mechanism enforces the overall effect of the more application rules of the comprehensive set of policy statements.  \r\nThus, any formal action manages to obtain this key principles. This can eventually cause certain issues.  ', 'SENT', 0),
(13, '1971-01-01 00:15:49', 1, 6, 'Speaking about comparison of a description of the primary element and user interface, a deliberate action of a broad understanding of the effective mechanism enforces the overall effect of the more application rules of the comprehensive set of policy statements.  \r\nThus, any formal action manages to obtain this key principles. This can eventually cause certain issues.  ', 'SENT', 0);


INSERT INTO notification(id, sent_time, contact, type_id, person_id, entity_id, enable) VALUES
(1, '1973-04-01 03:41:21', 'Deedra Houle', 6, 6, 6, true),
(2, '1981-03-30 12:38:17', 'Elmer Loftis', 5, 5, 5, false),
(3, '1992-09-22 22:34:26', 'Elwanda Rhodes', 3, 3, 3, true),
(4, '1978-12-16 12:15:09', 'Hiram Everhart', 6, 6, 6, true),
(5, '1988-12-06 07:51:38', 'Barry Oden', 9, 9, 9, true),
(6, '1971-01-01 01:10:15', 'Justine Avila', 4, 4, 4, true),
(7, '1972-04-07 20:19:22', 'Ezequiel Wenger', 3, 3, 3, true),
(8, '1971-01-01 01:8:31', 'Hanh Eddy', 5, 5, 5, true),
(9, '1971-01-01 00:06:2', 'Chantell Herron', 10, 10, 10, true),
(10, '1971-01-01 00:15:5', 'Shonta Bernstein', 8, 8, 8, true);

INSERT INTO post2tag(id, post_id, tag_id) VALUES
(1, 5, 5),
(2, 7, 7),
(3, 9, 9),
(4, 9, 9),
(5, 3, 3),
(6, 3, 3),
(7, 1, 1),
(8, 1, 1),
(9, 8, 8),
(10, 10, 10);

INSERT INTO person2dialog(id, dialog_id, person_id) VALUES
(1, 5, 5),
(2, 7, 7),
(3, 9, 9),
(4, 9, 9),
(5, 3, 3),
(6, 3, 3),
(7, 1, 1),
(8, 1, 1),
(9, 8, 8),
(10, 10, 10);

COMMIT;


# Library-Management
图书管理系统，实现加书、删书、借书、还书等操作

## 操作
- 1.Check Book
- 2.Add Book
- 3.Delete Book
- 4.Lend Book
- 5.Return Book
- 6.Exit

### 1.Check Book
> 得到图书馆里所有图书的信息（BookID图书编号、BookName图书名字、State在馆状态、BorrowedCount被借次数、BorrowedDate被借日期）

###2.Add Book
> 增加图书馆尚未存在的书

- 如果已存在该书，不加
- 如果未存在该书，增加


###3.Delete Book
> 删除馆里的书

- 如果不存在该书，不能删除
- 如果存在该书：
	- 如果在馆，可以删除
	- 如果被借出，不能删除

###4.Lend Book
> 借出馆里的书

- 如果不存在该书，不能借出
- 如果存在该书：
	- 如果在馆，可以借出，被借次数增加1，在馆状态变为“Borrowed”
	- 如果被借出，不能借出

###5.Return Book
> 归还馆里的书

- 如果不存在该书，不能归还
- 如果存在该书：
	- 如果在馆，不能归还
	- 如果被借出，可以归还，在馆状态变为“Available”
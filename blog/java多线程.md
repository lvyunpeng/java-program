1、多线程状态：
  新建（Thread thread = new Thread()），
  就绪（已经调用了start方法，等待获取cpu的使用权），
  运行（可运行状态获取到了cpu碎片），
  等待（TIME_WAITING， 调用了sleep(), wait(), join()等方法），
  阻塞（因为某种原因放弃了cpu的使用权，暂停运行，直到线程进入可执行状态Runnable，才可以转入运行状态；同步阻塞，如synchronized方法；异步阻塞，运行的线程发送了IO请求，JVM会把其加入阻塞状态，IO完成可进入可运行状态），
  销毁
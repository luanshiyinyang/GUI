import random
import tkinter


class Ball:
    """
    定义运动的球的类
    """
    def __init__(self, canvas, screen_width, screen_height):
        self.canvas = canvas
        self.xpos = random.randint(10, int(screen_width)-20)
        self.ypos = random.randint(10, int(screen_height)-20)
        self.xv = random.randint(4, 20)
        self.yv = random.randint(4, 20)
        self.screen_width = screen_width
        self.screen_height = screen_height
        self.radius = random.randint(20, 120)
        c = lambda: random.randint(0, 255)
        self.color = '#%02x%02x%02x' % (c(), c(), c())

    def create_ball(self):
        """
        在canvas上画球
        :return:
        """
        # tkinter没有画圆形函数,只有一个画椭圆函数，画椭圆需要定义两个坐标，
        # 在一个长方形内画椭圆，我们只需要定义长方形左上角和右下角就好
        # 求两个坐标的方法是，已知圆心的坐标，则圆心坐标减去半径能求出左上角坐标，加上半径能求出右下角坐标
        x1 = self.xpos - self.radius
        y1 = self.ypos - self.radius
        x2 = self.xpos + self.radius
        y2 = self.ypos + self.radius

        # 再有两个对角坐标的前提下，可以进行画圆
        # fill表示填充颜色
        # outline是外围边框颜色
        self.item = self.canvas.create_oval(x1, y1, x2, y2, fill=self.color, outline=self.color)

    def move_ball(self):
        """
        控制球的移动
        :return:
        """
        # 移动球的时候，需要控制球的方向
        # 每次移动后，球都有一个新的坐标
        self.xpos += self.xv
        self.ypos += self.yv
        # 以下判断是会否撞墙，撞墙回头
        if self.xpos + self.radius >= self.screen_width or self.xpos - self.radius <= 0:
            # 装到了右边墙
            self.xv = -self.xv
        if self.ypos + self.radius >= self.screen_height or self.ypos - self.radius <= 0:
            self.yv = -self.yv
        self.canvas.move(self.item, self.xv, self.yv)


class ScreenSaver:
    '''
    定义屏保的类
    可以被启动
    '''
    balls = list()

    def __init__(self):
        # 每次启动球的数量随机
        self.num_balls = random.randint(6, 20)

        self.root = tkinter.Tk()
        # 取消边框
        self.root.overrideredirect(1)

        # 这里只绑定了鼠标左键单击，可以自定义
        self.root.bind('<Button-1>', self.myquit)
        # 同理，按动任何键盘都需要退出屏保
        # 得到屏幕大小规格
        w, h = self.root.winfo_screenwidth(), self.root.winfo_screenheight()

        # 创建画布，包括画布的归属，规格
        self.canvas = tkinter.Canvas(self.root, width=w, height=h)
        self.canvas.pack()

        # 在画布上画球
        for i in range(self.num_balls):
            ball = Ball(self.canvas, screen_width=w, screen_height=h)
            ball.create_ball()
            self.balls.append(ball)

        self.run_screen_saver()
        self.root.mainloop()

    def run_screen_saver(self):
        """
        运行屏保
        :return:
        """
        for ball in self.balls:
            ball.move_ball()
        # after是200毫秒后启动一个函数，需要启动的函数是第二个参数
        self.canvas.after(30, self.run_screen_saver)

    def myquit(self, event):
        """
        退出
        :return:
        """
        self.root.destroy()


if __name__ == '__main__':
    ScreenSaver()

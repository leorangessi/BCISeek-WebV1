.sidebar-content {
  background: white;
  border-radius: 8px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
  padding: 1.5rem;
}<template>
  <div class="guide-page">
    <VisitorNavbar />
    
    <div class="guide-container">
      <aside class="sidebar">
        <div class="sidebar-content">
          <h3 class="sidebar-title">目录</h3>
          <ul class="sidebar-menu">
            <li v-for="(section, index) in sections" :key="index" class="sidebar-item">
              <a 
                :href="`#${section.id}`" 
                class="sidebar-link"
                @click="scrollToSection(section.id)"
                :class="{ active: activeSection === section.id }"
              >
                {{ section.title }}
              </a>
            </li>
          </ul>
        </div>
      </aside>
      
      <main class="content">
        <div class="content-wrapper">
          <h1 class="page-title">快速上手指南</h1>

<!-- 前言部分 -->
<section id="intro" class="guide-section">
  <h2 class="section-title">>前言</h2>
  <p class="section-content">
    欢迎使用 ExpandableBCI 可扩展脑机接口系统。本指南将帮助您快速上手使用我们的产品，包括引脚接线、佩戴说明、通信方式设置、采集模块使用等内容。
  </p>
  <p class="section-content">
    ExpandableEEG核心结构为基板（即顶层板、具有充电、主控等功能）和主采集板（即每张图的底层板），
    该基础结构构成了8通道采集板，在此基础上加装一块从采集板可扩展为16通道，加装三块从采集板可扩展为32通道。
    通过安装和拆卸从采集板，您可以调整采集板的通道数量。注意此操作需要关机下进行，操作完成后再开机，ExpandableEEG会自动调整好采集通道数。
  </p>
</section>
          
          <section id="pin-connection" class="guide-section">
            <h2 class="section-title">一、 引脚接线说明</h2>

             <div class="image-container">
                <img 
                     src="/images/图1 ExpandableEEG扩展，8通道、16通道以及32通道.png" 
                     alt="图2 ExpandableEEG接口分布" 
                        class="guide-image"
                                />
                        <p class="image-caption">图1 ExpandableEEG扩展，8通道、16通道以及32通道.png</p>
                </div>

            <p class="section-content">
              见图1左图，或图2，最左边引脚为BIAS（偏置信号），从左往右依次为8通道到1通道，接着是SRB2和SRB1。
            </p>
            <p class="section-content">
              <!-- <strong>图2 ExpandableEEG接口分布</strong> -->
               <div class="image-container">
                <img 
                     src="/images/expandableeeg-interface.png" 
                     alt="图2 ExpandableEEG接口分布" 
                        class="guide-image"
                                />
                        <p class="image-caption">图2 ExpandableEEG接口分布</p>
                </div>
            </p>
            <p class="section-content">
              对于16通道采集板而言，底层为1-8通道，第二层为9-16通道；对于32通道采集板而言，底层为1-8通道，第二层为9-16通道，第三层为17-24通道，第四层为25通道-32通道。
            </p>
            <p class="section-content">
              用于脑电采集时，如果走单端连接，当从采集板搭接上时，需要连接SRB和BUS_SRB、BIAS和BUS_BIAS引脚，如此每个板子可以共享SRB和BIAS引脚，默认SRB配置使用SRB2。脑电电极默认使用N通道（即弯排排针的底层）。
            </p>
            <p class="section-content">
              <!-- <strong>图3 ExpandableEEG16和ExpandableEEG32的EEG连接</strong> -->
                 <div class="image-container">
                <img 
                     src="/images/图3 ExpandableEEG16和ExpandableEEG32的EEG连接.png" 
                     alt="图2 ExpandableEEG接口分布" 
                        class="guide-image"
                                />
                        <p class="image-caption">图3 ExpandableEEG16和ExpandableEEG32的EEG连接</p>
                </div>
            </p>
            <p class="section-content">
              EEG连接也可以参考 <a href="#" class="external-link">Openbci GUI官网EEG连接</a>。
            </p>
            <p class="section-content">
              本模块为多模态，也支持同时采集肌电，肌电信号一般作为双端输入，需要同时利用N和P通道（此时可不需要连接SRB和BUS_SRB、BIAS和BUS_BIAS引脚）。在连接肌电电极后，需要使用OpenBCI GUI配置连接方式。进一步了解可以参考 <a href="#" class="external-link">Openbci GUI官网EMG连接</a>。
            </p>
            <p class="section-content">
              其他具体引脚说明可以参考芯片说明书《ads1299》p6引脚表或 <a href="#" class="external-link">ADS1299使用</a>。如此配置与Openbci GUI默认配置相匹配。
            </p>
          </section>
          
          <!-- 佩戴说明 -->
          <section id="wearing-guide" class="guide-section">
            <h2 class="section-title">二、 佩戴说明</h2>
            <p class="section-content">
              8通道和16通道的发货版电极所对应的地形图如下。
            </p>

              <div class="image-container">
                <img 
                     src="/images/地形.png" 
                     alt="图2 ExpandableEEG接口分布" 
                        class="guide-image"
                                />
                        <p class="image-caption"></p>
                </div>

            <p class="section-content">
              为确保每个电极与头皮直接有良好连接，保证头皮的干燥和卫生，注意长期佩戴会比较疼。在佩戴好后，可以使用cython signal控件测量当前电极接触区域的阻抗，阻抗越小信号质量越高。（注意，当前32通道暂无法使用此功能）
            </p>
            <p class="section-content">
              <!-- <strong>图4阻抗检测</strong> -->
                <div class="image-container">
                <img 
                     src="/images/图4阻抗检测.png" 
                     alt="图2 ExpandableEEG接口分布" 
                        class="guide-image"
                                />
                        <p class="image-caption">图4阻抗检测</p>
                </div>

            </p>
          </section>
          
          <!-- 更换通信方式 -->
          <section id="communication-mode" class="guide-section">
            <h2 class="section-title">三、 更换通信方式</h2>
            <p class="section-content">
              您可通过ExpandableEEG配置工具，将采集板配置为蓝牙、WiFi AP、WiFi client模式：在蓝牙模式下，采集数据稳定性更优、续航更长；在WiFi模式下，采样频率更高；在WiFi AP下，您的采集板可充当路由器，作为服务端，client模式下，则可以像手机一样接入您的路由器！出厂默认配置为WiFi AP。
            </p>
            <p class="section-content">
              给ExpandableEEG插上typec数据线连接电脑后，打开Tools->ExpandableEEG_Config.exe，点击“连接设备”即可匹配（32通道暂不支持），连接失败后也可以点击“重新连接”以重试。
            </p>
            <p class="section-content">
              连接完成后，点击进入设备，可以配置为以下模式：
            </p>
             <div class="image-container">
                <img 
                     src="/images/配置为以下模式.png" 
                     alt="图2 ExpandableEEG接口分布" 
                        class="guide-image"
                                />
                        <p class="image-caption"></p>
                </div>

            <ul class="section-list">
              <li class="list-item">
                <strong>①Wifi AP:</strong> 输入您想创建的wifi名称和wifi密码即可，点击配置后设备将重启。
              </li>
              <li class="list-item">
                <strong>②Wifi Client:</strong> 输入您想接入的wifi名称和wifi密码即可，点击配置后设备将重启。值得注意的是调试框会输出您的设备ip地址，此地址在连接设备时需要用到（Wifi AP默认为192.168.4.1）。
              </li>
            </ul>
            <div class="image-container">
                <img 
                     src="/images/串口.png" 
                     alt="图2 ExpandableEEG接口分布" 
                        class="guide-image"
                                />
                        <p class="image-caption"></p>
                </div>
            <p class="section-content">
              client模式下的ip最好记录下来，若未记录，您也可以在启动设备时打开串口，波特率选择115200，查看输出的ip地址
            </p>
            <div class="image-container">
                <img 
                     src="/images/成功.png" 
                     alt="图2 ExpandableEEG接口分布" 
                        class="guide-image"
                                />
                        <p class="image-caption"></p>
                </div>
            <ul class="section-list">
              <li class="list-item">
                <strong>③BT蓝牙模式：</strong>
                 <div class="image-container">
                <img 
                     src="/images/BT蓝牙模式.png" 
                     alt="图2 ExpandableEEG接口分布" 
                        class="guide-image"
                                />
                        <p class="image-caption"></p>
                </div>
              </li>

            </ul>
            <p class="section-content">
              随后在电脑上连接：
            </p>
            <div class="image-container">
                <img 
                     src="/images/连接.png" 
                     alt="图2 ExpandableEEG接口分布" 
                        class="guide-image"
                                />
                        <p class="image-caption"></p>
                </div>
            <p class="section-content">
              随后在你电脑上打开设置，找到“更多蓝牙设置”，方向为输出的映射COM口（图中为COM8）为您设备的端口
            </p>
             <div class="image-container">
                <img 
                     src="/images/电脑上打开设置.png" 
                     alt="图2 ExpandableEEG接口分布" 
                        class="guide-image"
                                />
                        <p class="image-caption"></p>
                </div>
          </section>
          
          <!-- 采集模块使用教程及Openbci GUI常用操作 -->
          <section id="gui-tutorial" class="guide-section">
            <h2 class="section-title">四、 采集模块使用教程及Openbci GUI常用操作</h2>
            <p class="section-content">
              打开Tools->openbci32->OpenBCI GUI。
            </p>
            <ul class="section-list">
              <li class="list-item">
                <strong>①wifi版本：</strong> 首先电脑连上wifi——“ExpandableEEG AP”，密码：abcd1234
                （注：一般电脑两个网卡，你可以使用网线上网）
              </li>
              <li class="list-item">
                <strong>蓝牙版本：</strong> 找到您的COM口后（上节所述）
              </li>
              <li class="list-item">
                <strong>②wifi版本：</strong> 打开软件Openbci GUI，进行以下操作（选择cyton->wifi->根据通道数选择CHANNEL COUNT，AP模式下IP地址为192.168.4.1，Client模式为配置时所输出的地址，您也可以在启动设备时打开串口查看输出ip）：
              </li>
            </ul>
             <div class="image-container">
                <img 
                     src="/images/wifi版本.png" 
                     alt="图2 ExpandableEEG接口分布" 
                        class="guide-image"
                                />
                        <p class="image-caption">图5 开始界面（wifi）</p>
                </div>
            <!-- <p class="section-content">
              <strong>图5开始界面（wifi）</strong>
            </p> -->
            <p class="section-content">
              <strong>蓝牙版本：</strong> 打开软件Openbci GUI，进行以下操作（选择cyton->serial->manual->你的COM口）
            </p>
            <div class="image-container">
                <img 
                     src="/images/蓝牙版本.png" 
                     alt="图2 ExpandableEEG接口分布" 
                        class="guide-image"
                                />
                        <p class="image-caption"></p>
                </div>
            <ul class="section-list">
              <li class="list-item">
                <strong>③</strong> 点击START SESSION按钮，即可开始采集。以下是对GUI的更多介绍：
              </li>
              <li class="list-item">
                <strong>④</strong> 进入后，有三个区域，可以点击每个区域左上角按钮进行界面选择，一般能用到的是FFT Plot（常用）、Focus、Networking（用于向python、matlab、open vibe等程序提供输出流，这个很常用）、Packet loss（查看丢包，最常用）、Marker（beta测试版提供，可用于打标）
              </li>
            </ul>
            <p class="section-content">

              <!-- <strong>图6界面介绍</strong> -->
                <div class="image-container">
                <img 
                     src="/images/图6界面介绍.png" 
                     alt="图2 ExpandableEEG接口分布" 
                        class="guide-image"
                                />
                        <p class="image-caption">图6界面介绍</p>
                </div>
            </p>
            <ul class="section-list">
              <li class="list-item">
                <strong>⑤</strong> 点击Start Data Stream，即可开始采集。未佩戴时，用手指刷一下电极（如下左图）可以看到界面有采集信号，说明所有通道均能采集。右上角的Vert Scale和Window可以调整窗口显示最大幅值、和窗口时间。（空置状态时，电极上有静电积累可能会产生右图所示的白噪声，可以使用手指刷一下电极或者等它消失）
              </li>
            </ul>
            <p class="section-content">
              <!-- <strong>图7采集示意</strong> -->
                 <div class="image-container">
                <img 
                     src="/images/图7 采集示意.png" 
                     alt="图2 ExpandableEEG接口分布" 
                        class="guide-image"
                                />
                        <p class="image-caption">图7 采集示意</p>
                </div>
            </p>
            <ul class="section-list">
              <li class="list-item">
                <strong>⑥</strong> 点击Hardware Setting可以进行硬件设置，按照前面介绍的连接方法基本不需操作，一些操作含义可参考芯片说明书《ads1299》p6引脚表或 <a href="#" class="external-link">ADS1299使用</a>，配置完成后，点Send按钮发送给采集模块。下图红色方框中可以直接输入命令字符来配置采集模块，并点击按钮发送给采集模块，具体操作及其他操作见： <a href="#" class="external-link">The OpenBCI GUI | OpenBCI Documentation</a>。（目前本采集模块不能支持全部cyton操作命令，支持~、1、2、3、4、5、6、7、8、！、@、#、$、%、^、&、*、x、b、s）
              </li>
            </ul>
            <p class="section-content">
              <!-- <strong>图8 GUI采集模块配置</strong> -->
                 <div class="image-container">
                <img 
                     src="/images/图8 GUI采集模块配置.png" 
                     alt="图2 ExpandableEEG接口分布" 
                        class="guide-image"
                                />
                        <p class="image-caption">图8 GUI采集模块配置</p>
                </div>
            </p>
            
            <h3 class="subsection-title">示例测试1-芯片内部信号采集测试：</h3>
            <ol class="section-ol">
              <li class="ol-item">配置通道输入类型为 Test ，并 Send 到采集模块，点 Time Series 回到波形图界面；</li>
              <li class="ol-item">修改显示最大幅值范围为 10000uV ，并点击 Filters ，在All中给所有通道设置为0.01Hz的最低采集频率；</li>
              <li class="ol-item">点击Start Data Stream，可以采集到如下图方波信号，不论幅值还是周期都与预想的一致，说明采集芯片到GUI这一条通路没问题。</li>
            </ol>
            <p class="section-content">
              <!-- <strong>图9 ads1299内部方波信号采集</strong> -->
                 <div class="image-container">
                <img 
                     src="/images/图9 ads1299内部方波信号采集.png" 
                     alt="图2 ExpandableEEG接口分布" 
                        class="guide-image"
                                />
                        <p class="image-caption">图9 ads1299内部方波信号采集</p>
                </div>
            </p>
            
            <h3 class="subsection-title">示例测试2-眼电信号采集测试：</h3>
            <p class="section-content">
              默认配置下，将脑电帽前额最下面两个电极（分别连接通道1、2）放于眉毛上1cm左右的地方，用力眨眼可看到以下波形：
            </p>
            <p class="section-content">
              <!-- <strong>图10眼电信号采集1</strong> -->
                 <div class="image-container">
                <img 
                     src="/images/图10眼电信号采集1.png" 
                     alt="图2 ExpandableEEG接口分布" 
                        class="guide-image"
                                />
                        <p class="image-caption">图10眼电信号采集1</p>
                </div>
            </p>
            <p class="section-content">
              在我先前的测试记录中，轻眨眼波形如下：（图中5、6通道为空，因为没接电极，我将这两电极连接到了SRB和BISA通道上并放在两耳垂上）
            </p>
            <p class="section-content">
              <!-- <strong>图11眼电信号采集2</strong> -->
  <div class="image-container">
                <img 
                     src="/images/图11眼电信号采集2.png" 
                     alt="图2 ExpandableEEG接口分布" 
                        class="guide-image"
                                />
                        <p class="image-caption">图11眼电信号采集2</p>
                </div>
            </p>
            
            <h3 class="subsection-title">其他示例：</h3>
            <p class="section-content">
              可参考 <a href="#" class="external-link">Example Projects | OpenBCI Documentation</a> 进行你感兴趣的工作，如P300拼写器、SSVEP或者EMG控制PPT、上下滚动页面等，源码在示例中有链接，附件中也有。这其中比较重要的一点是——使用Networking。我一般选择LSL，在GUI为采样状态时，选择Data Type，并点击Start LSL Stream开始发出流。注意：确保Type和Data Type与你程序中的一致（Name有时会用到，也需要确保一致），如果程序出现连接不上流，建议删除Type（Name）中的字符，并重新打一遍。
            </p>
            <p class="section-content">
              <!-- <strong>图12配置LSL流示意图</strong> -->
                 <div class="image-container">
                <img 
                     src="/images/图12配置LSL流示意图.png" 
                     alt="图2 ExpandableEEG接口分布" 
                        class="guide-image"
                                />
                        <p class="image-caption">图12配置LSL流示意图</p>
                </div>

            </p>
          </section>
          
          <!-- 如何开发 -->
          <section id="development" class="guide-section">
            <h2 class="section-title">如何开发</h2>
            
            <h3 class="subsection-title">一、数据保存</h3>
            <p class="section-content">
              这里推荐另一个方法，在有GUI情况下，SESSION DATA中会自动保存每一次session，每一次start及stop流之间的内容，路径信息及内容如下：
            </p>

            <p class="section-content">
              如果你想进一步开发程序，添加openbci的一些功能，可以参考附件里的源程序，或官方文档。
            </p>
             <div class="image-container">
                <img 
                     src="/images/路径信息.png" 
                     alt="图2 ExpandableEEG接口分布" 
                        class="guide-image"
                                />
                        <p class="image-caption"></p>
                </div>
            <h3 class="subsection-title">二、GUI开发介绍</h3>
            <p class="section-content">
              官方最新GUI程序在\EEG files\OpenBCI\OpenBCI_GUI，我个人修改过的Beta测试版本程序（有更多功能、可以进行COM连接）在\EEG files\Tools\openbci32，源码在\EEG files\OpenBCI\OpenBCI_GUI-master\OpenBCI_GUI-master\OpenBCI_GUI中，使用processing软件运行（附件中提供）即可（32通道功能代码未提供）。可参考： <a href="#" class="external-link">The OpenBCI GUI | OpenBCI Documentation</a>
            </p>
            <div class="image-container">
                <img 
                     src="/images/GUI程序.png" 
                     alt="图2 ExpandableEEG接口分布" 
                        class="guide-image"
                                />
                        <p class="image-caption"></p>
                </div>
          </section>
          
          <!-- 项目资料使用 -->
          <section id="project-materials" class="guide-section">
            <h2 class="section-title">项目资料使用</h2>
            
            <h3 class="subsection-title">一、Brainflow开发</h3>
            <p class="section-content">
              使用brainflow可以绕开Openbci GUI，直接获取EEG/EMG数据、查看波形，适用于开发者。参考：Openbci GUI官网及 <a href="#" class="external-link">brainflow官网</a>。
            </p>
            <p class="section-content">
              这里分享下我使用的命令：
            </p>
            <pre class="code-block">
1. python -u "e:\Interesting\BCI\brainflow-master\python_package\examples\plot_real_time\plot_real_time.py" --ip-address 192.168.4.1 --board 5  --ip-port 3
2. python -u "e:\Interesting\BCI\brainflow-master\python_package\examples\plot_real_time\plot_real_time.py" --serial-port COM3 --board 0
            </pre>
            <p class="section-content">
              效果如下：
            </p>
              <div class="image-container">
                <img 
                     src="/images/效果图.png" 
                     alt="图2 ExpandableEEG接口分布" 
                        class="guide-image"
                                />
                        <p class="image-caption"></p>
                </div>
            <p class="section-content">
              具体使用方式可自行看项目资料代码。
            </p>
            <p class="section-content">
              需要注意的是：
            </p>
            <p class="section-content">
              32通道需要使用tools文件夹里的boardController.dll替换您用python安装的版本。BoardId为56。
            </p>
             <div class="image-container">
                <img 
                     src="/images/需要注意.png" 
                     alt="图2 ExpandableEEG接口分布" 
                        class="guide-image"
                                />
                        <p class="image-caption"></p>
                </div>
            
            <h3 class="subsection-title">一、 运动想象实验</h3>
            <p class="section-content">
              在进行运动想象实验前，可以将所有电极放到大脑的运动皮层上方，运动皮层位置如下：
            </p>
             <div class="image-container">
                <img 
                     src="/images/运动皮层.png" 
                     alt="图2 ExpandableEEG接口分布" 
                        class="guide-image"
                                />
                        <p class="image-caption"></p>
                </div>
            <p class="section-content">
              首先调整脑电帽位置，将所有电极尽量覆盖运动皮层区域。
            </p>
            <p class="section-content">
              下面介绍如何使用BCI-Master进行运动想象实验。
            </p>
            <ol class="section-ol">
              <li class="ol-item">首先上电、连上wifi后，运行making_data.py文件，根据方块的移动分别想象左手握紧、右手握紧、放松三个动作。每次运行文件执行60次采集，大约每个动作采集120次，即运行6次文件即可。</li>
              <li class="ol-item">分割数据，运行spilit_data.py，将20%的数据放入validation_data下</li>
              <div class="image-container">
                <img 
                     src="/images/分割数据.png" 
                     alt="图2 ExpandableEEG接口分布" 
                        class="guide-image"
                                />
                        <p class="image-caption"></p>
                </div>
              <li class="ol-item">注释validation_data的语句，解注释test_data的语句，将10%的数据放入test_data下。</li>
              <li class="ol-item">训练数据，运行training.py，模型保存在new_models文件夹下。</li>
              <li class="ol-item">运行analysisAll.py，对所有模型进行测试集测试，画出混淆矩阵，从pictures文件夹中选择你最心意的模型。</li>
              <li class="ol-item">运行testing_data.py，重复训练时的想象动作，看实际测试效果。</li>
            </ol>
            <p class="section-content">
              实际测试时，大脑的疲惫感也可能导致与making_data时的大脑状态不同、第二次戴帽子时电极的偏移等均会对实验带来负面影响。大家可以对实验范式、算法进行改进。
            </p>
            
            <h3 class="subsection-title">三、SSVEP实验</h3>
            <p class="section-content">
              首先调整脑电帽位置，将所有电极尽量覆盖后脑勺。
            </p>
            <p class="section-content">
              下面介绍如何使用mind-speech-interface-ssvep-main进行运动想象实验。
            </p>
            <ol class="section-ol">
              <li class="ol-item">首先上电、连上wifi后，运行run_demo.py文件，根据提示，将注意力放在红色圆圈处，在黑白闪烁时，注意不要眨眼、不要转移注意力。</li>
               <div class="image-container">
                <img 
                     src="/images/运动想象实验.png" 
                     alt="图2 ExpandableEEG接口分布" 
                        class="guide-image"
                                />
                        <p class="image-caption"></p>
                </div>
              <li class="ol-item">实验完成后，在\BCI\mind-speech-interface-ssvep-main\demo_data文件夹中出现最新的.csv文件，将其拷贝至\BCI\mind-speech-interface-ssvep-main\mind-speech-interface-ssvep-main\SSVEP-Data-Collection\demo_data文件夹下，并拷贝下文件名称，粘贴在\BCI\mind-speech-interface-ssvep-main\mind-speech-interface-ssvep-main\EEG-Data-Visualization\ssvep-watolink_visualizer_version2.ipynb文件的如下位置：</li>
             <div class="image-container">
                <img 
                     src="/images/文件如下.png" 
                     alt="图2 ExpandableEEG接口分布" 
                        class="guide-image"
                                />
                        <p class="image-caption"></p>
                </div>
            </ol>
            
            <p class="section-content">
              运行所有单元格，可以看到在某些通道出现想要的波形。
            </p>
          </section>
        </div>
      </main>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted, watch, nextTick } from 'vue'
import { useRoute } from 'vue-router'
import VisitorNavbar from './VisitorNavbar.vue'

const route = useRoute()
const activeSection = ref('')

const sections = [
    { id: 'intro', title: '前言' },
  { id: 'pin-connection', title: '引脚接线说明' },
  { id: 'wearing-guide', title: '佩戴说明' },
  { id: 'communication-mode', title: '更换通信方式' },
  { id: 'gui-tutorial', title: '采集模块使用教程' },
  { id: 'development', title: '如何开发' },
  { id: 'project-materials', title: '项目资料使用' }
]

const scrollToSection = (id: string) => {
  activeSection.value = id
  const element = document.getElementById(id)
  if (element) {
    element.scrollIntoView({ behavior: 'smooth' })
  }
}

const updateActiveSection = () => {
  const scrollPosition = window.scrollY + 100
  
  for (const section of sections) {
    const element = document.getElementById(section.id)
    if (element) {
      const { offsetTop, offsetHeight } = element
      if (scrollPosition >= offsetTop && scrollPosition < offsetTop + offsetHeight) {
        activeSection.value = section.id
        break
      }
    }
  }
}

onMounted(() => {
  window.addEventListener('scroll', updateActiveSection)
  updateActiveSection()
})

watch(() => route.path, () => {
  nextTick(() => {
    updateActiveSection()
  })
})
</script>

<style lang="scss" scoped>
.guide-page {
  min-height: 100vh;
  background: #f8f9fa;
  padding-top: 70px;
}

.guide-container {
  display: flex;
  max-width: 1400px;
  margin: 0 auto;
  padding: 2rem;
  gap: 2rem;
}

.sidebar {
  flex: 0 0 230px;
  position: sticky;
  top: 90px;
  height: fit-content;
  max-height: calc(100vh - 100px);
  overflow-y: auto;
}

.sidebar-content {
  background: white;
  border-radius: 8px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
  padding: 1.5rem;
}

.sidebar-title {
  font-size: 1.2rem;
  font-weight: 600;
  margin-bottom: 1rem;
  color: #333;
  border-bottom: 1px solid #eaeaea;
  padding-bottom: 0.5rem;
}

.sidebar-menu {
  list-style: none;
  padding: 0;
  margin: 0;
}

.sidebar-item {
  margin-bottom: 0.25rem;
}

.sidebar-link {
  display: block;
  padding: 0.5rem 0.75rem;
  color: #555;
  text-decoration: none;
  border-radius: 4px;
  transition: all 0.2s;
  font-size: 0.95rem;
  cursor: pointer;
  
  &:hover {
    background: #f0f0f0;
    color: #667eea;
  }
  
  &.active {
    background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
    color: white;
    font-weight: 500;
  }
}

.content {
  flex: 1;
  min-width: 0;
}

.content-wrapper {
  background: white;
  border-radius: 8px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
  padding: 2rem;
}

.page-title {
  font-size: 2.5rem;
  font-weight: 700;
  margin-bottom: 2rem;
  color: #333;
  text-align: center;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  background-clip: text;
}

.guide-section {
  margin-bottom: 3rem;
  padding-bottom: 2rem;
  border-bottom: 1px solid #eaeaea;
  
  &:last-child {
    border-bottom: none;
    margin-bottom: 0;
    padding-bottom: 0;
  }
}

.section-title {
  font-size: 1.8rem;
  font-weight: 600;
  margin-bottom: 1.5rem;
  color: #333;
  padding-bottom: 0.5rem;
  border-bottom: 2px solid #667eea;
}

.subsection-title {
  font-size: 1.4rem;
  font-weight: 600;
  margin: 1.5rem 0 1rem 0;
  color: #444;
}

.section-content {
  font-size: 1rem;
  line-height: 1.6;
  color: #555;
  margin-bottom: 1rem;
}

.section-list {
  margin: 1rem 0 1.5rem 0;
  padding-left: 1.5rem;
}

.list-item {
  margin-bottom: 0.75rem;
  line-height: 1.6;
  color: #555;
}

.section-ol {
  margin: 1rem 0 1.5rem 0;
  padding-left: 1.5rem;
}

.ol-item {
  margin-bottom: 0.75rem;
  line-height: 1.6;
  color: #555;
}

.code-block {
  background: #f5f5f5;
  border-radius: 4px;
  padding: 1rem;
  font-family: 'Courier New', Courier, monospace;
  font-size: 0.9rem;
  line-height: 1.4;
  margin: 1rem 0;
  overflow-x: auto;
  white-space: pre-wrap;
  color: #333;
}

.external-link {
  color: #667eea;
  text-decoration: none;
  transition: color 0.2s;
  
  &:hover {
    color: #764ba2;
    text-decoration: underline;
  }
}

@media (max-width: 1024px) {
  .guide-container {
    flex-direction: column;
    padding: 1rem;
  }
  
  .sidebar {
    flex: 1;
    position: relative;
    top: 0;
    max-height: none;
  }
  
  .content-wrapper {
    padding: 1.5rem;
  }
  
  .page-title {
    font-size: 2rem;
  }
  
  .section-title {
    font-size: 1.5rem;
  }
  
  .subsection-title {
    font-size: 1.2rem;
  }
}

@media (max-width: 768px) {
  .content-wrapper {
    padding: 1rem;
  }
  
  .page-title {
    font-size: 1.8rem;
  }
  
  .section-title {
    font-size: 1.3rem;
  }
  
  .subsection-title {
    font-size: 1.1rem;
  }
}

.image-container {
  text-align: center;
  margin: 1.5rem 0;
}

.guide-image {
  max-width: 100%;
  height: auto;
  border-radius: 8px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
}

.image-caption {
  margin-top: 0.5rem;
  font-size: 0.9rem;
  color: #666;
  font-weight: 500;
}
</style>
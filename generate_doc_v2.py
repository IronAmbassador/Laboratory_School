#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
实验室管理系统 - 技术开发文档生成脚本（按模板格式）
"""

from docx import Document
from docx.shared import Inches, Pt
from docx.enum.text import WD_ALIGN_PARAGRAPH
import os

def create_doc_by_template():
    """按照模板格式创建新文档"""
    doc = Document()
    
    # 标题
    title = doc.add_heading('《实验室管理系统》', 0)
    title.alignment = WD_ALIGN_PARAGRAPH.CENTER
    doc.add_heading('项目技术开发文档', level=1)
    
    # 修订表格
    doc.add_heading('修订记录', level=2)
    table = doc.add_table(rows=3, cols=5)
    table.style = 'Table Grid'
    hdr_cells = table.rows[0].cells
    hdr_cells[0].text = '修订日期'
    hdr_cells[1].text = '修订内容'
    hdr_cells[2].text = '修订人'
    hdr_cells[3].text = '审核人'
    hdr_cells[4].text = '审核意见'
    
    # 系统功能模块总体结构图
    doc.add_heading('系统功能模块总体结构图', level=2)
    doc.add_paragraph('图1 系统功能模块总体结构图')
    doc.add_paragraph('【此处插入系统功能模块总体结构图】')
    
    # 系统各功能模块的设计
    doc.add_heading('系统各功能模块的设计', level=2)
    
    # 2.1 用户登录
    doc.add_heading('2.1 用户登录', level=3)
    doc.add_heading('（1）功能描述：', level=4)
    doc.add_paragraph(
        '用户通过输入用户名、密码和验证码进行登录验证，验证通过后获取Token进入系统。'
    )
    
    doc.add_heading('（2）设计思路：', level=4)
    doc.add_paragraph(
        '用户在登录页面输入账号、密码和验证码，前端将数据通过POST请求发送到后端。'
        '后端接收数据后，首先验证验证码的正确性，然后查询数据库验证用户名和密码，'
        '密码使用MD5加密存储。验证通过后生成JWT Token返回给前端，前端将Token存储在本地，'
        '后续请求携带Token进行身份认证。'
    )
    
    doc.add_heading('（3）关键技术：', level=4)
    doc.add_paragraph(
        '前端使用Vue.js + Element UI构建登录界面，使用Axios发送HTTP请求。'
        '后端使用Spring Boot框架，使用MyBatis Plus进行数据库操作，'
        '使用JWT进行Token生成和验证，使用Hutool工具类进行MD5加密。'
    )
    
    doc.add_heading('前端主要代码：', level=4)
    login_code = '''import request from '@/utils/request'

export function login(data) {
  return request({
    url: '/login',
    method: 'post',
    data
  })
}

export function getInfo(token) {
  return request({
    url: '/info',
    method: 'get',
    params: { token }
  })
}

export function logout() {
  return request({
    url: '/logout',
    method: 'post'
  })
}'''
    doc.add_paragraph(login_code)
    
    doc.add_heading('后端主要代码：', level=4)
    login_controller_code = '''@Api(tags = "登录管理")
@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "登录", notes = "用户登录")
    @PostMapping("/login")
    public Result<Object> login(@RequestBody UserLoginDto userLoginDto) {
        return Result.success(userService.login(userLoginDto));
    }

    @ApiOperation(value = "获取用户信息", notes = "根据 Token 获取用户信息")
    @GetMapping("/info")
    public Result<User> getInfo(String token) {
        return Result.success(userService.getInfo(token));
    }
}'''
    doc.add_paragraph(login_controller_code)
    
    user_service_code = '''@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

    @Override
    public Map<String, Object> login(UserLoginDto userLoginDto) {
        String username = userLoginDto.getName();

        // 根据用户输入的用户名得到用户信息
        User u = baseMapper.selectOne(
            new LambdaQueryWrapper<User>()
                .eq(StrUtil.isNotBlank(username), User::getUsername, username)
        );

        // 判断用户是否为空
        if (ObjectUtil.isNull(u)) {
            throw new LabException(ResultCodeEnum.USERNAME_NOT_FOUND);
        }

        // 判断用户状态是否被禁用
        if (u.getStatus() == 1) {
            throw new LabException(ResultCodeEnum.USER_DISABLED);
        }

        // 判断用户输入的密码和当前查询的用户密码是否一致
        if (!u.getPassword().equals(DigestUtil.md5Hex(userLoginDto.getPwd()))) {
            throw new LabException(ResultCodeEnum.PASSWORD_ERROR);
        }

        Map<String, String> claimMap = new HashMap<>();
        claimMap.put("userId", u.getId().toString());
        claimMap.put("username", u.getUsername());
        claimMap.put("nickname", u.getNickname());

        String token = JwtUtils.createToken(claimMap);

        // 返回登录信息
        HashMap<String, Object> map = new HashMap<>();
        map.put("token", token);

        return map;
    }
}'''
    doc.add_paragraph(user_service_code)
    
    doc.add_heading('（4）运行界面（截图）', level=4)
    doc.add_paragraph('图2 登录界面')
    doc.add_paragraph('【此处插入登录界面截图】')
    
    # 2.2 实验室预约管理
    doc.add_heading('2.2 实验室预约管理', level=3)
    doc.add_heading('（1）功能描述：', level=4)
    doc.add_paragraph(
        '用户可以查看实验室信息，选择可用时间段提交预约申请。管理员可以查看所有预约申请，'
        '进行审核操作（通过/拒绝）。预约状态包括：待审核、已通过、已拒绝、已取消。'
    )
    
    doc.add_heading('（2）设计思路：', level=4)
    doc.add_paragraph(
        '用户在预约页面选择实验室和时间段，填写预约备注后提交申请。'
        '后端接收预约请求，首先检查实验室在该时间段是否开放，然后检查是否存在时间冲突，'
        '验证通过后保存预约记录，初始状态为待审核。管理员可以在预约管理页面查看所有预约，'
        '对预约进行审核操作，更新预约状态。'
    )
    
    doc.add_heading('（3）关键技术：', level=4)
    doc.add_paragraph(
        '使用Spring Boot的RESTful API进行数据交互，使用MyBatis Plus进行数据库操作，'
        '使用PageHelper进行分页查询，使用自定义注解@SysLog记录操作日志。'
    )
    
    doc.add_heading('前端主要代码：', level=4)
    doc.add_paragraph('【此处插入前端预约相关代码】')
    
    doc.add_heading('后端主要代码：', level=4)
    booking_controller_code = '''@Api(tags = "实验室预约管理")
@RestController
@RequestMapping("/admin/lab-booking")
public class BookingController extends BaseController<BookingService, Booking> {

    @ApiOperation(value = "分页查询所有实验室预约信息")
    @PostMapping("/page/{pIndex}/{pSize}")
    public Result<PageInfo<BookingVO>> getPage(
            @PathVariable Integer pIndex,
            @PathVariable Integer pSize,
            @RequestBody(required = false) BookingQueryDTO labBookingQueryDTO) {
        return Result.success(
            new PageInfo<>(baseService.getAll(pIndex, pSize, labBookingQueryDTO))
        );
    }

    @ApiOperation(value = "实验室预约审核")
    @SysLog(type = OperationTypeEnum.UPDATE)
    @PutMapping("/process")
    public Result<Boolean> process(@RequestBody BookingProcessDTO labBookingProcessDTO) {
        return baseService.process(labBookingProcessDTO) 
            ? Result.success() : Result.failure();
    }
}'''
    doc.add_paragraph(booking_controller_code)
    
    booking_service_code = '''@Service
public class BookingServiceImpl extends ServiceImpl<BookingMapper, Booking>
        implements BookingService {

    @Autowired
    private OpenMapper openMapper;

    @Override
    public boolean save(Booking entity) {
        // 判断实验室是否在开放时间
        int openCount = openMapper.countOpenByTime(
                entity.getLabId(),
                entity.getStartTime(),
                entity.getEndTime()
        );

        if (openCount == 0) {
            throw new RuntimeException("预约失败：实验室在所选时间段不开放");
        }

        entity.setUserId(SecurityHolderUtils.getUserId());
        return super.save(entity);
    }

    @Override
    public Boolean process(BookingProcessDTO labBookingProcessDTO) {
        Booking labBooking = new Booking();
        labBooking.setId(labBookingProcessDTO.getId());
        labBooking.setBookingStatus(labBookingProcessDTO.getBookingStatus());
        return super.updateById(labBooking);
    }
}'''
    doc.add_paragraph(booking_service_code)
    
    booking_entity_code = '''@ApiModel(value = "实验室预约")
@Data
@EqualsAndHashCode(callSuper = true)
public class Booking extends BaseEntity implements Serializable {

    @ApiModelProperty(value = "实验室id")
    private Integer labId;

    @ApiModelProperty(value = "用户id")
    private Integer userId;

    @ApiModelProperty(value = "预约开始时间")
    private Date startTime;

    @ApiModelProperty(value = "预约结束时间")
    private Date endTime;

    @ApiModelProperty(value = "预约状态: 0-审核中，1-已预约，2-未通过")
    private Integer bookingStatus;

    @ApiModelProperty(value = "备注")
    private String remark;
}'''
    doc.add_paragraph(booking_entity_code)
    
    doc.add_heading('（4）运行界面（截图）', level=4)
    doc.add_paragraph('图3 预约申请界面                              图4 预约审核界面')
    doc.add_paragraph('【此处插入预约申请界面截图】')
    doc.add_paragraph('【此处插入预约审核界面截图】')
    
    # 2.3 实验室管理
    doc.add_heading('2.3 实验室管理', level=3)
    doc.add_heading('（1）功能描述：', level=4)
    doc.add_paragraph(
        '管理员可以对实验室信息进行管理，包括实验室的添加、修改、删除和查询。'
        '实验室信息包括实验室名称、位置、容量、设备配置等。'
    )
    
    doc.add_heading('（2）设计思路：', level=4)
    doc.add_paragraph(
        '管理员进入实验室管理页面，可以查看所有实验室列表，支持分页查询和条件搜索。'
        '点击新增按钮可以添加新的实验室，填写实验室基本信息后保存。'
        '点击编辑按钮可以修改实验室信息，点击删除按钮可以删除实验室（逻辑删除）。'
    )
    
    doc.add_heading('（3）关键技术：', level=4)
    doc.add_paragraph(
        '使用Element UI的表格组件展示数据，使用表单组件进行数据录入，'
        '后端使用MyBatis Plus的IService接口提供基础的CRUD操作。'
    )
    
    doc.add_heading('前端主要代码：', level=4)
    doc.add_paragraph('【此处插入前端实验室管理相关代码】')
    
    doc.add_heading('后端主要代码：', level=4)
    doc.add_paragraph('【此处插入后端实验室管理相关代码】')
    
    doc.add_heading('（4）运行界面（截图）', level=4)
    doc.add_paragraph('图5 实验室列表界面   图6 实验室新增/编辑界面')
    doc.add_paragraph('【此处插入实验室列表界面截图】')
    doc.add_paragraph('【此处插入实验室新增/编辑界面截图】')
    
    # 2.4 设备管理
    doc.add_heading('2.4 设备管理', level=3)
    doc.add_heading('（1）功能描述：', level=4)
    doc.add_paragraph(
        '管理实验室的设备信息，包括设备名称、购买时间、价格、保修信息、生产厂商、设备状态等。'
        '支持设备的增删改查操作，以及设备维修管理。'
    )
    
    doc.add_heading('（2）设计思路：', level=4)
    doc.add_paragraph(
        '在设备管理页面可以查看所有设备信息，按实验室进行筛选。'
        '新增设备时选择所属实验室，填写设备详细信息。设备状态包括正常、维修中、已报废等。'
        '当设备需要维修时，可以提交维修申请，记录维修信息。'
    )
    
    doc.add_heading('（3）关键技术：', level=4)
    doc.add_paragraph(
        '使用EasyExcel进行设备信息的导入导出，使用MyBatis Plus进行数据库操作。'
    )
    
    doc.add_heading('前端主要代码：', level=4)
    doc.add_paragraph('【此处插入前端设备管理相关代码】')
    
    doc.add_heading('后端主要代码：', level=4)
    doc.add_paragraph('【此处插入后端设备管理相关代码】')
    
    doc.add_heading('（4）运行界面（截图）', level=4)
    doc.add_paragraph('图7 设备列表界面   图8 设备维修界面')
    doc.add_paragraph('【此处插入设备列表界面截图】')
    doc.add_paragraph('【此处插入设备维修界面截图】')
    
    # 2.5 课程管理
    doc.add_heading('2.5 课程管理', level=3)
    doc.add_heading('（1）功能描述：', level=4)
    doc.add_paragraph(
        '管理实验室的课程安排，包括课程名称、任课教师、上课时间、下课时间、使用实验室等信息。'
        '支持Excel批量导入课程信息。'
    )
    
    doc.add_heading('（2）设计思路：', level=4)
    doc.add_paragraph(
        '课程管理页面展示课程列表，可以按时间、实验室等条件筛选。'
        '支持单个添加课程，也支持使用Excel模板批量导入课程信息，'
        '导入时会进行数据校验，避免时间冲突。'
    )
    
    doc.add_heading('（3）关键技术：', level=4)
    doc.add_paragraph(
        '使用EasyExcel进行Excel文件的读取和写入，使用Hutool工具类进行日期处理。'
    )
    
    doc.add_heading('前端主要代码：', level=4)
    doc.add_paragraph('【此处插入前端课程管理相关代码】')
    
    doc.add_heading('后端主要代码：', level=4)
    doc.add_paragraph('【此处插入后端课程管理相关代码】')
    
    doc.add_heading('（4）运行界面（截图）', level=4)
    doc.add_paragraph('图9 课程列表界面   图10 课程导入界面')
    doc.add_paragraph('【此处插入课程列表界面截图】')
    doc.add_paragraph('【此处插入课程导入界面截图】')
    
    # 2.6 系统管理
    doc.add_heading('2.6 系统管理', level=3)
    doc.add_heading('2.6.1 用户管理', level=4)
    doc.add_heading('（1）功能描述：', level=4)
    doc.add_paragraph(
        '管理员可以管理系统用户，包括用户的添加、修改、删除、角色分配等。'
        '用户角色包括管理员、教师、学生等。'
    )
    
    doc.add_heading('（2）设计思路：', level=4)
    doc.add_paragraph(
        '用户管理页面展示用户列表，支持按用户名、角色、状态等条件筛选。'
        '新增用户时设置初始密码（MD5加密存储），可以禁用/启用用户账号。'
        '删除用户时保护管理员账号和当前登录用户不被删除。'
    )
    
    doc.add_heading('（3）关键技术：', level=4)
    doc.add_paragraph(
        '使用JWT进行身份认证，使用Sa-Token进行权限控制，使用MD5进行密码加密。'
    )
    
    doc.add_heading('前端主要代码：', level=4)
    doc.add_paragraph('【此处插入前端用户管理相关代码】')
    
    doc.add_heading('后端主要代码：', level=4)
    doc.add_paragraph('【此处插入后端用户管理相关代码】')
    
    doc.add_heading('（4）运行界面（截图）', level=4)
    doc.add_paragraph('图11 用户列表界面')
    doc.add_paragraph('【此处插入用户列表界面截图】')
    
    doc.add_heading('2.6.2 日志管理', level=4)
    doc.add_heading('（1）功能描述：', level=4)
    doc.add_paragraph(
        '记录用户的登录日志和操作日志，包括登录时间、IP地址、操作内容、操作时间等信息，'
        '便于系统审计和追溯。'
    )
    
    doc.add_heading('（2）设计思路：', level=4)
    doc.add_paragraph(
        '使用AOP切面编程，在用户登录和执行关键操作时自动记录日志。'
        '登录日志记录登录时间、IP地址、登录状态等。操作日志记录操作类型、操作内容、操作人等。'
        '管理员可以在日志管理页面查询和导出日志信息。'
    )
    
    doc.add_heading('（3）关键技术：', level=4)
    doc.add_paragraph(
        '使用Spring AOP进行切面编程，使用自定义注解@SysLog标记需要记录日志的方法，'
        '使用ip2region进行IP地址解析。'
    )
    
    doc.add_heading('前端主要代码：', level=4)
    doc.add_paragraph('【此处插入前端日志管理相关代码】')
    
    doc.add_heading('后端主要代码：', level=4)
    doc.add_paragraph('【此处插入后端日志管理相关代码】')
    
    doc.add_heading('（4）运行界面（截图）', level=4)
    doc.add_paragraph('图12 登录日志界面   图13 操作日志界面')
    doc.add_paragraph('【此处插入登录日志界面截图】')
    doc.add_paragraph('【此处插入操作日志界面截图】')
    
    # 保存文档
    output_path = '/workspace/实验室管理系统开发技术报告.docx'
    doc.save(output_path)
    print(f"文档已生成: {output_path}")
    return output_path

if __name__ == '__main__':
    output_file = create_doc_by_template()
    print(f"技术开发文档生成完成！")

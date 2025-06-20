import request from '@/utils/request'

class ApiClient {
  /**
   * 构造函数
   *
   * @param {String} apiName 接口名称
   */
  constructor(apiName) {
    this.apiName = apiName
  }

  // GET请求方法
  get(endpoint) {
    return request({
      url: `${this.apiName}/${endpoint}`,
      method: 'get'
    })
  }

  // POST请求方法
  post(endpoint, data) {
    return request({
      url: `${this.apiName}/${endpoint}`,
      method: 'post',
      data: data
    })
  }

  // PUT请求方法
  put(endpoint, data) {
    return request({
      url: `${this.apiName}/${endpoint}`,
      method: 'put',
      data: data
    })
  }

  // DELETE请求方法
  delete(endpoint, data) {
    return request({
      url: `${this.apiName}/${endpoint}`,
      method: 'delete',
      data: data
    })
  }

  /**
   * 查询所有
   *
   * @returns
   */
  getList() {
    return this.get(`list`)
  }

  /**
   * 分页查询
   *
   * @param {Number} pIndex 当前页
   * @param {Number} pSize  每页大小
   * @param {Number} searchData 查询条件
   * @returns
   */
  getPage(pIndex, pSize, searchData) {
    return this.post(`page/${pIndex}/${pSize}`, searchData)
  }

  /**
   * 根据ID查询
   *
   * @param {Number} id
   * @returns
   */
  getById(id) {
    return this.get(`get/${id}`)
  }

  /**
   *  修改
   *
   * @param {Object} form
   * @returns
   */
  update(form) {
    return this.put('update', form)
  }

  /**
   * 保存
   *
   * @param {Object} form
   * @returns
   */
  save(form) {
    return this.post('save', form)
  }

  /**
   * 根据ID删除
   *
   * @param {Number} id
   * @returns
   */
  remove(id) {
    return this.delete(`remove/${id}`)
  }

  /**
   * 批量删除
   *
   * @param {Array} ids
   * @returns
   */
  batchRemove(ids) {
    return this.delete(`batchRemove`, ids)
  }

  /**
   * 导入
   *
   * @returns
   */
  importData(filePath) {
    return request({
      url: `${this.apiName}/importData`,
      method: 'post',
      params: { filePath: filePath }
    })
  }

  /**
   * 导出
   *
   * @returns
   */
  exportData() {
    return request({
      url: `${this.apiName}/exportData`,
      method: 'get',
      responseType: 'blob'
    })
  }
}

export default ApiClient // 导出ApiClient类

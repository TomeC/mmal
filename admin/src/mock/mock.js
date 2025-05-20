const Mock = require('mockjs')

Mock.mock('api/public/jsconfig/getcrmebchatconfig', (req, res) => {
    return {
        code: '200'
    }
});
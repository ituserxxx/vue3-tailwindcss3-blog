
const diyfunc = {
    formatDate(dateString) {
        dateString = dateString.value;
        // 创建 Date 对象
        const date = new Date(dateString);
        // 检查日期对象是否有效
        if (isNaN(date.getTime())) {
            console.error('Invalid date');
            return '';
        }
        // 获取各个部分
        const year = date.getFullYear();
        const month = String(date.getMonth() + 1).padStart(2, '0'); // 月份从0开始
        const day = String(date.getDate()).padStart(2, '0');
        const hours = String(date.getHours()).padStart(2, '0');
        const minutes = String(date.getMinutes()).padStart(2, '0');
        const seconds = String(date.getSeconds()).padStart(2, '0');

        // 拼接成目标格式
        return `${year}-${month}-${day} ${hours}:${minutes}:${seconds}`;
    },
}
export default diyfunc;

export default class DateFormatter {
  static getTodayString() {
    const pad2 = (n) => (n < 10 ? '0' : '') + n;

    const date = new Date();
    const month = pad2(date.getMonth() + 1); //months (0-11)
    const day = pad2(date.getDate()); //day (1-31)
    const year = date.getFullYear();

    return year + '-' + month + '-' + day; // 'YYYY-mm-dd'
  }

  static getOneYearLaterString() {
    const today = this.getTodayString();
    return +today.split('-')[0] + 1 + today.substr(4);
  }
}

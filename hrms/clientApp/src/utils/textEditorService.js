export default class TextEditorService {
  static capitalize(sentence) {
    return sentence
      .split(' ')
      .map((word) => word[0].toLocaleUpperCase() + word.substr(1))
      .join(' ');
  }
}

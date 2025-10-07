# SnapShelf 📸✨
Smart product management with auto-generated tags and descriptions.

## 📝 概要
個人学習用の Spring Boot Web アプリケーション。
商品画像を登録すると AI が自動でタグを抽出し、説明文を生成する商品管理システムです。

現在は基本的な CRUD 機能とユーザー認証、Vision API連携を実装しRenderへデプロイ済みで、Gemini　API連携やAWSへのデプロイは今後追加予定です。

## デモ
🚀 **https://snapshelf-hs6j.onrender.com**

**注意：** 無料プランのため、初回アクセス時は起動に30秒〜1分ほどかかります。

### テストアカウント
- メールアドレス: test@test
- パスワード: test

## 📷スクリーンショット

**ホーム画面**

<img src="readme-assets/home.png" alt="ホーム画面" width="300">

**商品登録画面**

<img src="readme-assets/productcreate.png" alt="ホーム画面" width="300">

**説明文生成ボタン押下時**

現在は、Vision APIによって抽出されたタグをもとにGemini連携時に渡すプロンプトを仮表示します。

<img src="readme-assets/prompt.png" alt="プロンプト" width="300">

**商品一覧画面**

<img src="readme-assets/shelfview.png" alt="ホーム画面" width="300">

## ✨ 実装済み機能
- ✅ ユーザー登録・ログイン（Spring Security）
- ✅ 商品情報の登録・一覧表示、検索・編集・保存
- ✅ Spring Data JPA によるデータベース操作
- ✅ Thymeleaf による画面表示
- ✅ Vue.js による部分的な動的処理
- ✅ Renderへのデプロイ
- ✅ Google Vision API による画像解析・タグ自動生成

## 🚧 開発予定
- ⏳ Gemini API による商品説明文の自動生成
- ⏳ AWS (EC2 + RDS) へのデプロイ
- ⏳ タグでの分類、入出庫管理などの拡張

## 🛠️ 使用技術

**バックエンド**
- Java 17
- Spring Boot 3.5.3
- Spring Security / Spring MVC / Spring Data JPA
- Thymeleaf
- Maven（ビルド・依存管理）

**データベース**
- PostgreSQL

**フロントエンド**
- HTML / CSS
- JavaScript
- Vue.js（一部導入）

**外部API**
- Vision API

**その他**
- Git / GitHub / GitHub Desktop
- Eclipse → 9月に STS に変更

## 📚 開発履歴
- **2025年5月**
Java学習を開始。
- 開発環境構築（Eclipse, Git, GitHub Desktop, Maven）
- データベースはまだ未導入
- 基本的な開発手順や CRUD 作成方法を試して学習
- Spring Boot の基礎を習得

- **2025年7月**
趣味開発として *popoalbum* を開始（画像投稿・表示が中心の小規模Webアプリ）。
- 画像アップロード／表示の実装
- 初期の画面・ルーティング設計

- **2025年7月下旬〜8月**
アプリを拡張し本格開発へ。
- PostgreSQL 接続・DB 設計（商品・ユーザーなど）
- ユーザー新規登録・認証画面の実装
- 画像アップロードの品質改善

- **2025年9月（リファクタリング → 改名）**
新しいPCで開発環境を再構築（STS に移行）
Java Silver SE17 を取得。
大規模リファクタリングを行い、プロジェクト名を *SnapShelf* に変更。
主な改善：
- PRG（Post-Redirect-Get）パターン導入による投稿フロー改善
- Service 層分離、例外処理整備
- Spring Security によるログイン認証／初回ログインチュートリアル分岐
- Vue.js 試験導入（動的 UI、フロント → DTO の連携修正）
- 商品一覧に検索・ページングを追加、一覧 → 詳細遷移
- 説明文自動生成（AI連携の土台）の実装準備
- UI改善・バグ修正（画像表示、フォームバリデーションなど）

## 👤 作者
GitHub: [@poposay](https://github.com/poposay)

## 📄 ライセンス
This project is licensed under the MIT License.

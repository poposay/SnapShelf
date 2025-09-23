# Popo Album

個人学習用のSpring Boot Webアプリ。  
投稿した写真に対してAIがコメントします。  
現在、投稿・閲覧機能とログイン認証（Spring Security）は実装済み。  
外部API連携（Vision API / Gemini API）やAWSデプロイは開発予定です。

---

## スクリーンショット

<img src="readme-assets/sample.png" alt="画面イメージ" width="300"> <br>
<img src="readme-assets/sample2.png" alt="画面イメージ2" width="300">

---

## 現在の機能

- ユーザー登録・ログイン（Spring Security導入済み）  
- 写真投稿・一覧閲覧  
- 投稿ごとのAIコメント（ダミーデータで表示）


---

## 今後の実装予定

- 外部API連携  
  - Google Vision APIで画像解析  
  - Gemini APIでコメント生成  
- AWSへのデプロイ（EC2 + S3）  
- タイムライン機能・いいね機能などの拡張

---

## 🚀使用技術

<h3>🖥️ フロントエンド</h3>
• Thymeleaf <br>
• HTML / CSS<br>


<h3>🧠 バックエンド</h3>
• Java 17<br>
• Spring Boot 3.2.0<br>
• Spring Security<br>
• Spring MVC<br>
• Spring Data JPA<br>


<h3>💾 データベース</h3>
• PostgreSQL<br>


<h3>🧰 その他</h3>
• Maven（ビルドツール）<br>
• Git / GitHub（バージョン管理）<br>

<h3>📚予定している外部API連携</h3>
• Gemini API<br>
• Vision API<br>

---
## 📝学習目的

このプロジェクトは以下の技術習得を目的としています：
	•	Spring Bootエコシステムの理解
	•	セキュリティ機能の実装
	•	外部API連携の実践
	•	クラウドインフラへのデプロイ経験

---

## ⚒️設計
<img src="readme-assets/er.png" width="250"> 
<img src="readme-assets/arc.png" width="250">

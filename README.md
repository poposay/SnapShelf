# SnapShelf

個人学習用のSpring Boot Webアプリ。  
商品画像を登録するとAIが自動でタグ抽出し説明文を生成します。  
現在、商品登録・一覧表示・ログイン認証（Spring Security）は実装済み。  
外部API連携（Vision API / Gemini API）やAWSデプロイは開発予定です。

---

## 📷スクリーンショット



---

## ✅現在の機能

- ユーザー登録・ログイン（Spring Security導入済み）  
- 商品情報の登録・一覧表示
- AIによる商品説明（ダミーデータで表示）


---

## 今後の実装予定

- 外部API連携  
  - Google Vision APIで画像解析  
  - Gemini APIでコメント生成  
- AWSへのデプロイ（EC2 + S3）  
- お気に入り・レビュー機能などの拡張

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

<img src="readme-assets/arc.png" width="250">

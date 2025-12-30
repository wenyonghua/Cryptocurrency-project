import { createI18n } from 'vue-i18n'

const messages = {
    en: {
        common: { confirm: 'Confirm', cancel: 'Cancel', copy: 'Copy', submit: 'Submit', all: 'All', no_data: 'No Data', available: 'Available' },
        home: { new_user_bonus: 'New User Bonus', sign_up_bonus: 'Sign up to get 100 USDT coupon', seconds_contract: 'Seconds Contract', seconds_desc: 'Up to 90% Profit in 30s', market_trends: 'Market Trends', hot: 'Hot', forex: 'Forex', gold: 'Gold', global: 'Global', credit_score: 'Credit Score', notice: 'System Maintenance Notice' },
        market: { title: 'Market', vol: 'Vol', high: 'High', low: 'Low', change: 'Change', markets: 'Markets', favorites: 'Favorites', spot: 'Spot', futures: 'Futures', pair: 'Pair', last_price: 'Last Price', chg: 'Chg', no_favorites: 'No favorites', seconds: 'Seconds', cycle: 'Cycle', search_coin: 'Search Coin' },
        login: { welcome: 'Welcome Back', account: 'Account', username: 'Username', username_req: 'Username is required', password: 'Password', password_req: 'Password is required', btn: 'Log In', forgot: 'Forgot Password?', register: 'Register' },
        grid: { mining: 'Mining', loan: 'Loan', swap: 'Swap', cycle: 'Cycle', promotion: 'Promotion', spot: 'Spot', seconds: 'Seconds', finance: 'Finance', ieo: 'IEO', deposit: 'Deposit', withdraw: 'Withdraw', support: 'Support' },
        nav: { home: 'Home', market: 'Market', trade: 'Trade', assets: 'Assets', mine: 'Mine' },
        cycle: { title: 'Cycle', buy: 'Buy Bull', sell: 'Sell Bear', market_price: 'Market Price' },
        drawer: { primary_cert: 'Primary Certification', advanced_cert: 'Advanced Certification', fund_pwd: 'Set Fund Password', login_pwd: 'Set Login Password', email_auth: 'Email Authentication', terms: 'Service Terms', help: 'Help Center', language: 'Language', logout: 'Log Out' },
        mine: { security: 'Security Center', identity: 'Identity Verification', payment: 'Payment Methods', language: 'Language', help: 'Help Center', about: 'About Us', logout: 'Log Out', verified: 'Verified', unverified: 'Unverified' },
        trade: {

            contract: 'Contract', look_up: 'Look Up', buy_long: 'Buy Long', look_down: 'Look Down', buy_short: 'Buy Short',
            duration: 'Duration', profit: 'Profit', amount: 'Amount', balance: 'Balance', confirm_order: 'Confirm Order',
            spot_soon: 'Spot Trading (Coming Soon)', buy: 'Buy', sell: 'Sell', avail: 'Avail', open_orders: 'Open Orders',
            no_open_orders: 'No open orders', price: 'Price', amt: 'Amt', limit_order: 'Limit Order', market_order: 'Market Order'
        },
        assets: {

            deposit: 'Deposit', withdraw: 'Withdraw', chain_name: 'Chain Name', deposit_addr: 'Deposit Address',
            copy_addr: 'Copy Address', min_deposit: 'Minimum deposit amount', withdraw_addr: 'Withdraw Address',
            paste_addr: 'Paste address', fee: 'Fee', fund_pwd: 'Fund Password', enter_pwd: 'Enter 6-digit password',
            total_assets: 'Total Assets (USDT)', transfer: 'Transfer', asset: 'Asset', avail: 'Avail', frozen: 'Frozen',
            notice_1: 'Please do not deposit any non-USDT assets to this address.', notice_2: 'Minimum deposit amount: 10 USDT.', min_withdraw: 'Min withdrawal',
            platform_assets: 'Platform Assets', financial_assets: 'Financial Assets', contract_assets: 'Contract Assets', account_balance: 'Account Balance',
            details: 'Asset Details', occupied: 'Occupied', equivalent: 'Equivalent (USDT)', swap: 'Swap'
        }
    },
    zh: {
        common: { confirm: '确定', cancel: '取消', copy: '复制', submit: '提交', all: '全部', no_data: '暂无数据', available: '可用' },
        home: { new_user_bonus: '新用户福利', sign_up_bonus: '注册即送 100 USDT 券', seconds_contract: '秒合约', seconds_desc: '30秒高达90%收益', market_trends: '市场趋势', hot: '热门', forex: '外汇', gold: '黄金', global: '全球站', credit_score: '信用分', notice: '系统维护公告' },
        market: { title: '行情', vol: '成交量', high: '最高', low: '最低', change: '涨跌', markets: '行情', favorites: '自选', spot: '币币交易', futures: '合约', pair: '名称', last_price: '最新价', chg: '涨跌幅', no_favorites: '暂无自选', seconds: '秒合约', cycle: '周期合约交易', search_coin: '搜索币种' },
        login: { welcome: '欢迎回来', account: '账号', username: '用户名', username_req: '请输入用户名', password: '密码', password_req: '请输入密码', btn: '登录', forgot: '忘记密码?', register: '注册账号' },
        grid: { mining: '质押挖矿', loan: '助力贷', swap: '闪兑', cycle: '周期合约', promotion: '推广中心', spot: '币币交易', seconds: '秒合约', finance: '理财', ieo: 'IEO认购', deposit: '快捷充币', withdraw: '快速提币', support: '在线客服' },
        nav: { home: '首页', market: '行情', trade: '交易', assets: '资产', mine: '我的' },
        cycle: { title: '周期合约', buy: '买涨', sell: '买跌', market_price: '市场价' },
        drawer: { primary_cert: '初级认证', advanced_cert: '高级认证', fund_pwd: '设置资金密码', login_pwd: '设置登录密码', email_auth: '邮箱认证', terms: '服务条款', help: '帮助中心', language: '语言设置', logout: '退出登录' },
        mine: { security: '安全中心', identity: '身份认证', payment: '收款方式', language: '语言设置', help: '帮助中心', about: '关于我们', logout: '退出登录', verified: '已认证', unverified: '未认证' },
        trade: {

            contract: '合约', look_up: '看涨', buy_long: '买涨', look_down: '看跌', buy_short: '买跌',
            duration: '周期', profit: '收益', amount: '金额', balance: '余额', confirm_order: '确认下单',
            spot_soon: '币币交易 (即将开放)', buy: '买入', sell: '卖出', avail: '可用', open_orders: '当前委托',
            no_open_orders: '暂无委托', price: '价格', amt: '数量', limit_order: '限价单', market_order: '市价单'
        },
        assets: {

            deposit: '充币', withdraw: '提币', chain_name: '链名称', deposit_addr: '充币地址',
            copy_addr: '复制地址', min_deposit: '最小充值金额', withdraw_addr: '提币地址',
            paste_addr: '粘贴地址', fee: '手续费', fund_pwd: '资金密码', enter_pwd: '输入6位密码',
            total_assets: '总资产 (USDT)', transfer: '划转', asset: '币种', avail: '可用', frozen: '冻结',
            notice_1: '请勿充值非USDT资产到此地址。', notice_2: '最小充值金额: 10 USDT。', min_withdraw: '最小提币',
            platform_assets: '平台资产', financial_assets: '理财资产', contract_assets: '合约资产', account_balance: '账户余额',
            details: '资产明细', occupied: '占用', equivalent: '折合 (USDT)', swap: '闪兑'
        }
    },
    jp: {
        common: { confirm: '確認', cancel: 'キャンセル', copy: 'コピー', submit: '送信', all: 'すべて', no_data: 'データなし', available: '利用可能' },
        home: { new_user_bonus: '新規ユーザー特典', sign_up_bonus: '100 USDTクーポンをゲット', seconds_contract: '秒間契約', seconds_desc: '30秒で最大90%の利益', market_trends: '市場動向', hot: '人気', forex: '外国為替', gold: 'ゴールド', global: 'グローバル', credit_score: 'クレジットスコア', notice: 'システムメンテナンスのお知らせ' },
        market: { title: '市場', vol: 'Vol', high: '高値', low: '安値', change: '変動', markets: '市場', favorites: 'お気に入り', spot: '現物', futures: '先物', pair: 'ペア', last_price: '最新価格', chg: '変動率', no_favorites: 'お気に入りなし', seconds: '秒契約', cycle: 'サイクル契約', search_coin: 'コイン検索' },
        login: { welcome: 'お帰りなさい', account: 'アカウント', username: 'ユーザー名', username_req: 'ユーザー名が必要です', password: 'パスワード', password_req: 'パスワードが必要です', btn: 'ログイン', forgot: 'パスワードをお忘れですか？', register: '登録' },
        grid: { mining: 'マイニング', loan: 'ローン', swap: 'スワップ', cycle: 'サイクル', promotion: 'プロモーション', spot: '現物取引', seconds: '秒間契約', finance: '金融', ieo: 'IEO', deposit: '入金', withdraw: '出金', support: 'サポート' },
        nav: { home: 'ホーム', market: '市場', trade: '取引', assets: '資産', mine: '私の' },
        cycle: { title: 'サイクル', buy: '買い', sell: '売り', market_price: '市場価格' },
        drawer: { primary_cert: '基本認証', advanced_cert: '高度認証', fund_pwd: '資金パスワード設定', login_pwd: 'ログインパスワード設定', email_auth: 'メール認証', terms: '利用規約', help: 'ヘルプセンター', language: '言語', logout: 'ログアウト' },
        mine: { security: 'セキュリティセンター', identity: '身元確認', payment: '支払い方法', language: '言語', help: 'ヘルプセンター', about: '私たちについて', logout: 'ログアウト', verified: '確認済み', unverified: '未確認' },
        trade: {

            contract: '契約', look_up: '強気', buy_long: 'ロング購入', look_down: '弱気', buy_short: 'ショート購入',
            duration: '期間', profit: '利益', amount: '金額', balance: '残高', confirm_order: '注文確認',
            spot_soon: '現物取引 (近日公開)', buy: '購入', sell: '売却', avail: '利用可能', open_orders: 'オープンオーダー',
            no_open_orders: 'オープンオーダーなし', price: '価格', amt: '数量', limit_order: '指値注文', market_order: '成行注文'
        },
        assets: {

            deposit: '入金', withdraw: '出金', chain_name: 'チェーン名', deposit_addr: '入金アドレス',
            copy_addr: 'アドレスをコピー', min_deposit: '最低入金額', withdraw_addr: '出金アドレス',
            paste_addr: 'アドレスを貼り付け', fee: '手数料', fund_pwd: '資金パスワード', enter_pwd: '6桁のパスワード',
            total_assets: '総資産 (USDT)', transfer: '振替', asset: '通貨', avail: '可能', frozen: '凍結',
            notice_1: 'このアドレスにUSDT以外の資産を入金しないでください。', notice_2: '最低入金額: 10 USDT。', min_withdraw: '最低出金',
            platform_assets: 'プラットフォーム資産', financial_assets: '金融資産', contract_assets: '契約資産', account_balance: '口座残高',
            details: '資産詳細', occupied: '占有', equivalent: '換算 (USDT)', swap: 'スワップ'
        }
    },
    kr: {
        common: { confirm: '확인', cancel: '취소', copy: '복사', submit: '제출', all: '전체', no_data: '데이터 없음', available: '가능' },
        home: { new_user_bonus: '신규 사용자 보너스', sign_up_bonus: '가입하고 100 USDT 받기', seconds_contract: '초단기 계약', seconds_desc: '30초 만에 최대 90% 수익', market_trends: '시장 동향', hot: '인기', forex: '외환', gold: '금', global: '글로벌', credit_score: '신용 점수', notice: '시스템 유지 보수 공지' },
        market: { title: '시장', vol: '거래량', high: '최고', low: '최저', change: '변동', markets: '시장', favorites: '즐겨찾기', spot: '현물', futures: '선물', pair: '페어', last_price: '최신가', chg: '변동률', no_favorites: '즐겨찾기 없음', seconds: '초단기', cycle: '주기 계약', search_coin: '코인 검색' },
        login: { welcome: '환영합니다', account: '계정', username: '사용자 이름', username_req: '사용자 이름을 입력하세요', password: '비밀번호', password_req: '비밀번호를 입력하세요', btn: '로그인', forgot: '비밀번호를 잊으셨나요?', register: '등록' },
        grid: { mining: '채굴', loan: '대출', swap: '스왑', cycle: '주기', promotion: '프로모션', spot: '현물', seconds: '초단기', finance: '금융', ieo: 'IEO', deposit: '입금', withdraw: '출금', support: '지원' },
        nav: { home: '홈', market: '시장', trade: '거래', assets: '자산', mine: '마이' },
        cycle: { title: '주기', buy: '매수', sell: '매도', market_price: '시장가' },
        drawer: { primary_cert: '기본 인증', advanced_cert: '고급 인증', fund_pwd: '자금 비밀번호 설정', login_pwd: '로그인 비밀번호 설정', email_auth: '이메일 인증', terms: '서비스 약관', help: '도움말 센터', language: '언어', logout: '로그아웃' },
        mine: { security: '보안 센터', identity: '신원 확인', payment: '지불 방법', language: '언어', help: '도움말 센터', about: '회사 소개', logout: '로그아웃', verified: '확인됨', unverified: '미확인' },
        trade: {

            contract: '계약', look_up: '매수', buy_long: '롱 오픈', look_down: '매도', buy_short: '숏 오픈',
            duration: '기간', profit: '수익', amount: '금액', balance: '잔액', confirm_order: '주문 확인',
            spot_soon: '현물 거래 (곧 출시)', buy: '매수', sell: '매도', avail: '가능', open_orders: '미체결 주문',
            no_open_orders: '미체결 주문 없음', price: '가격', amt: '수량', limit_order: '지정가 주문', market_order: '시장가 주문'
        },
        assets: {

            deposit: '입금', withdraw: '출금', chain_name: '체인 이름', deposit_addr: '입금 주소',
            copy_addr: '주소 복사', min_deposit: '최소 입금액', withdraw_addr: '출금 주소',
            paste_addr: '주소 붙여넣기', fee: '수수료', fund_pwd: '자금 비밀번호', enter_pwd: '6자리 비밀번호 입력',
            total_assets: '총 자산 (USDT)', transfer: '이체', asset: '자산', avail: '가능', frozen: '동결',
            notice_1: '이 주소로 USDT 이외의 자산을 입금하지 마십시오.', notice_2: '최소 입금액: 10 USDT.', min_withdraw: '최소 출금',
            platform_assets: '플랫폼 자산', financial_assets: '금융 자산', contract_assets: '계약 자산', account_balance: '계정 잔액',
            details: '자산 세부 정보', occupied: '점유', equivalent: '환산 (USDT)', swap: '스왑'
        }
    },
    vn: {
        common: { confirm: 'Xác nhận', cancel: 'Hủy', copy: 'Sao chép', submit: 'Gửi', all: 'Tất cả', no_data: 'Không có dữ liệu', available: 'Có sẵn' },
        home: { new_user_bonus: 'Thưởng người dùng mới', sign_up_bonus: 'Đăng ký nhận phiếu 100 USDT', seconds_contract: 'Hợp đồng giây', seconds_desc: 'Lợi nhuận tới 90% trong 30s', market_trends: 'Xu hướng thị trường', hot: 'Hot', forex: 'Ngoại hối', gold: 'Vàng', global: 'Toàn cầu', credit_score: 'Điểm tín dụng', notice: 'Thông báo bảo trì hệ thống' },
        market: { title: 'Thị trường', vol: 'KL', high: 'Cao', low: 'Thấp', change: 'Thay đổi', markets: 'Thị trường', favorites: 'Yêu thích', spot: 'Giao ngay', futures: 'Hợp đồng', pair: 'Cặp', last_price: 'Giá mới nhất', chg: 'Thay đổi', no_favorites: 'Không có yêu thích', seconds: 'Hợp đồng giây', cycle: 'Hợp đồng chu kỳ', search_coin: 'Tìm kiếm coin' },
        login: { welcome: 'Chào mừng trở lại', account: 'Tài khoản', username: 'Tên đăng nhập', username_req: 'Vui lòng nhập tên đăng nhập', password: 'Mật khẩu', password_req: 'Vui lòng nhập mật khẩu', btn: 'Đăng nhập', forgot: 'Quên mật khẩu?', register: 'Đăng ký' },
        grid: { mining: 'Khai thác', loan: 'Vay', swap: 'Hoán đổi', cycle: 'Chu kỳ', promotion: 'Khuyến mãi', spot: 'Giao ngay', seconds: 'Hợp đồng giây', finance: 'Tài chính', ieo: 'IEO', deposit: 'Nạp tiền', withdraw: 'Rút tiền', support: 'Hỗ trợ' },
        nav: { home: 'Trang chủ', market: 'Thị trường', trade: 'Giao dịch', assets: 'Tài sản', mine: 'Của tôi' },
        cycle: { title: 'Chu kỳ', buy: 'Mua', sell: 'Bán', market_price: 'Giá thị trường' },
        drawer: { primary_cert: 'Xác minh cơ bản', advanced_cert: 'Xác minh nâng cao', fund_pwd: 'Mật khẩu quỹ', login_pwd: 'Mật khẩu đăng nhập', email_auth: 'Xác thực email', terms: 'Điều khoản', help: 'Trung tâm trợ giúp', language: 'Ngôn ngữ', logout: 'Đăng xuất' },
        mine: { security: 'Trung tâm bảo mật', identity: 'Xác minh danh tính', payment: 'Phương thức thanh toán', language: 'Ngôn ngữ', help: 'Trung tâm trợ giúp', about: 'Về chúng tôi', logout: 'Đăng xuất', verified: 'Đã xác minh', unverified: 'Chưa xác minh' },
        trade: {

            contract: 'Hợp đồng', look_up: 'Xem lên', buy_long: 'Mua lên', look_down: 'Xem xuống', buy_short: 'Mua xuống',
            duration: 'Thời gian', profit: 'Lợi nhuận', amount: 'Số tiền', balance: 'Số dư', confirm_order: 'Xác nhận',
            spot_soon: 'Giao dịch ngay (Sắp ra mắt)', buy: 'Mua', sell: 'Bán', avail: 'Có sẵn', open_orders: 'Lệnh đang mở',
            no_open_orders: 'Không có lệnh nào', price: 'Giá', amt: 'Số lượng', limit_order: 'Lệnh giới hạn', market_order: 'Lệnh thị trường'
        },
        assets: {

            deposit: 'Nạp tiền', withdraw: 'Rút tiền', chain_name: 'Tên chuỗi', deposit_addr: 'Địa chỉ nạp',
            copy_addr: 'Sao chép', min_deposit: 'Số tiền nạp tối thiểu', withdraw_addr: 'Địa chỉ rút',
            paste_addr: 'Dán địa chỉ', fee: 'Phí', fund_pwd: 'Mật khẩu quỹ', enter_pwd: 'Nhập mật khẩu 6 số',
            total_assets: 'Tổng tài sản (USDT)', transfer: 'Chuyển', asset: 'Tài sản', avail: 'Có sẵn', frozen: 'Đóng băng',
            notice_1: 'Vui lòng không nạp bất kỳ tài sản nào ngoài USDT vào địa chỉ này.', notice_2: 'Số tiền nạp tối thiểu: 10 USDT.', min_withdraw: 'Rút tối thiểu',
            platform_assets: 'Tài sản nền tảng', financial_assets: 'Tài sản tài chính', contract_assets: 'Tài sản hợp đồng', account_balance: 'Số dư tài khoản',
            details: 'Chi tiết tài sản', occupied: 'Chiếm dụng', equivalent: 'Tương đương (USDT)', swap: 'Hoán đổi'
        }
    },
    th: {
        common: { confirm: 'ยืนยัน', cancel: 'ยกเลิก', copy: 'คัดลอก', submit: 'ส่ง', all: 'ทั้งหมด', no_data: 'ไม่มีข้อมูล', available: 'ใช้ได้' },
        home: { new_user_bonus: 'โบนัสผู้ใช้ใหม่', sign_up_bonus: 'ลงทะเบียนรับคูปอง 100 USDT', seconds_contract: 'สัญญาระยะสั้น', seconds_desc: 'กำไรสูงสุด 90% ใน 30 วินาที', market_trends: 'แนวโน้มตลาด', hot: 'ยอดนิยม', forex: 'Forex', gold: 'ทองคำ', global: 'ทั่วโลก', credit_score: 'คะแนนเครดิต', notice: 'ประกาศปิดปรับปรุงระบบ' },
        market: { title: 'ตลาด', vol: 'ปริมาณ', high: 'สูง', low: 'ต่ำ', change: 'เปลี่ยนแปลง', markets: 'ตลาด', favorites: 'รายการโปรด', spot: 'สปอต', futures: 'ฟิวเจอร์ส', pair: 'คู่', last_price: 'ราคาล่าสุด', chg: 'เปลี่ยนแปลง', no_favorites: 'ไม่มีรายการโปรด', seconds: 'สัญญาระยะสั้น', cycle: 'สัญญาตามรอบ', search_coin: 'ค้นหาเหรียญ' },
        login: { welcome: 'ยินดีต้อนรับกลับ', account: 'บัญชี', username: 'ชื่อผู้ใช้', username_req: 'กรุณากรอกชื่อผู้ใช้', password: 'รหัสผ่าน', password_req: 'กรุณากรอกรหัสผ่าน', btn: 'เข้าสู่ระบบ', forgot: 'ลืมรหัสผ่าน?', register: 'ลงทะเบียน' },
        grid: { mining: 'การขุด', loan: 'สินเชื่อ', swap: 'แลกเปลี่ยน', cycle: 'รอบ', promotion: 'โปรโมชั่น', spot: 'สปอต', seconds: 'วินาที', finance: 'การเงิน', ieo: 'IEO', deposit: 'ฝาก', withdraw: 'ถอน', support: 'สนับสนุน' },
        nav: { home: 'หน้าแรก', market: 'ตลาด', trade: 'เทรด', assets: 'สินทร พย์', mine: 'ฉัน' },
        cycle: { title: 'รอบ', buy: 'ซื้อ', sell: 'ขาย', market_price: 'ราคาตลาด' },
        drawer: { primary_cert: 'การรับรองเบื้องต้น', advanced_cert: 'การรับรองขั้นสูง', fund_pwd: 'รหัสผ่านกองทุน', login_pwd: 'รหัสผ่านเข้าสู่ระบบ', email_auth: 'ยืนยันอีเมล', terms: 'เงื่อนไขบริการ', help: 'ศูนย์ช่วยเหลือ', language: 'ภาษา', logout: 'ออกจากระบบ' },
        mine: { security: 'ศูนย์ความปลอดภัย', identity: 'การยืนยันตัวตน', payment: 'วิธีการชำระเงิน', language: 'ภาษา', help: 'ศูนย์ช่วยเหลือ', about: 'เกี่ยวกับเรา', logout: 'ออกจากระบบ', verified: 'ยืนยันแล้ว', unverified: 'ยังไม่ยืนยัน' },
        trade: {

            contract: 'สัญญา', look_up: 'ดูขึ้น', buy_long: 'ซื้อขึ้น', look_down: 'ดูลง', buy_short: 'ซื้อลง',
            duration: 'ระยะเวลา', profit: 'กำไร', amount: 'จำนวน', balance: 'ยอดคงเหลือ', confirm_order: 'ยืนยัน',
            spot_soon: 'การซื้อขายสปอต (เร็วๆ นี้)', buy: 'ซื้อ', sell: 'ขาย', avail: 'ว่าง', open_orders: 'คำสั่งที่เปิดอยู่',
            no_open_orders: 'ไม่มีคำสั่งที่เปิดอยู่', price: 'ราคา', amt: 'จำนวน', limit_order: 'คำสั่งลิมิต', market_order: 'คำสั่งตลาด'
        },
        assets: {

            deposit: 'ฝากเงิน', withdraw: 'ถอนเงิน', chain_name: 'ชื่อเครือข่าย', deposit_addr: 'ที่อยู่ฝาก',
            copy_addr: 'คัดลอก', min_deposit: 'ฝากขั้นต่ำ', withdraw_addr: 'ที่อยู่ถอน',
            paste_addr: 'วางที่อยู่', fee: 'ค่าธรรมเนียม', fund_pwd: 'รหัสผ่านกองทุน', enter_pwd: 'ป้อนรหัสผ่าน 6 หลัก',
            total_assets: 'ทรัพย์สินทั้งหมด (USDT)', transfer: 'โอน', asset: 'สินทรัพย์', avail: 'ว่าง', frozen: 'ถูกล็อค',
            notice_1: 'โปรดอย่าฝากสินทรัพย์ใดๆ นอกเหนือจาก USDT ไปยังที่อยู่นี้', notice_2: 'จำนวนเงินฝากขั้นต่ำ: 10 USDT', min_withdraw: 'ถอนขั้นต่ำ',
            platform_assets: 'สินทรัพย์แพลตฟอร์ม', financial_assets: 'สินทรัพย์ทางการเงิน', contract_assets: 'สินทรัพย์สัญญา', account_balance: 'ยอดคงเหลือบัญชี',
            details: 'รายละเอียดสินทรัพย์', occupied: 'ถูกยึดครอง', equivalent: 'เทียบเท่า (USDT)', swap: 'แลกเปลี่ยน'
        }
    },
    es: {
        common: { confirm: 'Confirmar', cancel: 'Cancelar', copy: 'Copiar', submit: 'Enviar', all: 'Todo', no_data: 'Sin Datos', available: 'Disponible' },
        home: { new_user_bonus: 'Bono Nuevo Usuario', sign_up_bonus: 'Regístrate por 100 USDT', seconds_contract: 'Contrato Segundos', seconds_desc: '90% Lucro en 30s', market_trends: 'Tendencias', hot: 'Hot', forex: 'Forex', gold: 'Oro', global: 'Global', credit_score: 'Puntaje', notice: 'Aviso de Mantenimiento' },
        market: { title: 'Mercado', vol: 'Vol', high: 'Alto', low: 'Bajo', change: 'Cambio', markets: 'Mercados', favorites: 'Favoritos', spot: 'Spot', futures: 'Futuros', pair: 'Par', last_price: 'Último Precio', chg: 'Cambio', no_favorites: 'Sin favoritos', seconds: 'Segundos', cycle: 'Ciclo', search_coin: 'Buscar moneda' },
        login: { welcome: 'Bienvenido de Nuevo', account: 'Cuenta', username: 'Usuario', username_req: 'Usuario requerido', password: 'Password', password_req: 'Password requerido', btn: 'Iniciar Sesión', forgot: '¿Olvidó Password?', register: 'Registrar' },
        grid: { mining: 'Minería', loan: 'Préstamo', swap: 'Intercambio', cycle: 'Ciclo', promotion: 'Promoción', spot: 'Spot', seconds: 'Segundos', finance: 'Finanzas', ieo: 'IEO', deposit: 'Depósito', withdraw: 'Retiro', support: 'Soporte' },
        nav: { home: 'Inicio', market: 'Mercado', trade: 'Comercio', assets: 'Activos', mine: 'Mío' },
        cycle: { title: 'Ciclo', buy: 'Comprar', sell: 'Vender', market_price: 'Precio de mercado' },
        drawer: { primary_cert: 'Certificación Primaria', advanced_cert: 'Certificación Avanzada', fund_pwd: 'Clave de Fondo', login_pwd: 'Clave de Acceso', email_auth: 'Auth Email', terms: 'Términos', help: 'Ayuda', language: 'Idioma', logout: 'Cerrar Sesión' },
        mine: { security: 'Centro de Seguridad', identity: 'Verificación de Identidad', payment: 'Métodos de Pago', language: 'Idioma', help: 'Centro de Ayuda', about: 'Sobre Nosotros', logout: 'Cerrar Sesión', verified: 'Verificado', unverified: 'No Verificado' },
        trade: {

            contract: 'Contrato', look_up: 'Subir', buy_long: 'Comprar Largo', look_down: 'Bajar', buy_short: 'Comprar Corto',
            duration: 'Duración', profit: 'Lucro', amount: 'Monto', balance: 'Balance', confirm_order: 'Confirmar',
            spot_soon: 'Comercio Spot (Próximamente)', buy: 'Comprar', sell: 'Vender', avail: 'Disp', open_orders: 'Órdenes Abiertas',
            no_open_orders: 'Sin órdenes', price: 'Precio', amt: 'Cant', limit_order: 'Orden Límite', market_order: 'Orden de Mercado'
        },
        assets: {

            deposit: 'Depósito', withdraw: 'Retiro', chain_name: 'Nombre Cadena', deposit_addr: 'Dirección Depósito',
            copy_addr: 'Copiar', min_deposit: 'Mínimo depósito', withdraw_addr: 'Dirección Retiro',
            paste_addr: 'Pegar dirección', fee: 'Tarifa', fund_pwd: 'Clave Fondo', enter_pwd: 'Ingresar 6 dígitos',
            total_assets: 'Activos Totales (USDT)', transfer: 'Transferencia', asset: 'Activo', avail: 'Disp', frozen: 'Congelado',
            notice_1: 'Por favor no deposite activos que no sean USDT en esta dirección.', notice_2: 'Depósito mínimo: 10 USDT.', min_withdraw: 'Mínimo retiro',
            platform_assets: 'Activos Plataforma', financial_assets: 'Activos Financieros', contract_assets: 'Activos Contrato', account_balance: 'Saldo Cuenta',
            details: 'Detalles Activos', occupied: 'Ocupado', equivalent: 'Equivalente (USDT)', swap: 'Intercambio'
        }
    },
    fr: {
        common: { confirm: 'Confirmer', cancel: 'Annuler', copy: 'Copier', submit: 'Soumettre', all: 'Tout', no_data: 'Pas de données', available: 'Disponible' },
        home: { new_user_bonus: 'Bonus Nouvel Utilisateur', sign_up_bonus: 'Inscrivez-vous pour 100 USDT', seconds_contract: 'Contrat Secondes', seconds_desc: 'Profit 90% en 30s', market_trends: 'Tendances', hot: 'Hot', forex: 'Forex', gold: 'Or', global: 'Global', credit_score: 'Score Crédit', notice: 'Avis de Maintenance' },
        market: { title: 'Marché', vol: 'Vol', high: 'Haut', low: 'Bas', change: 'Change', markets: 'Marchés', favorites: 'Favoris', spot: 'Spot', futures: 'Futures', pair: 'Paire', last_price: 'Dernier Prix', chg: 'Change', no_favorites: 'Aucun favori', seconds: 'Secondes', cycle: 'Cycle', search_coin: 'Rechercher' },
        login: { welcome: 'Bon retour', account: 'Compte', username: 'Nom d\'utilisateur', username_req: 'Nom d\'utilisateur requis', password: 'Mot de passe', password_req: 'Mot de passe requis', btn: 'Connexion', forgot: 'Mot de passe oublié ?', register: 'S\'inscrire' },
        grid: { mining: 'Minage', loan: 'Prêt', swap: 'Échange', cycle: 'Cycle', promotion: 'Promotion', spot: 'Spot', seconds: 'Secondes', finance: 'Finances', ieo: 'IEO', deposit: 'Dépôt', withdraw: 'Retrait', support: 'Support' },
        nav: { home: 'Accueil', market: 'Marché', trade: 'Traiter', assets: 'Actifs', mine: 'Moi' },
        cycle: { title: 'Cycle', buy: 'Acheter', sell: 'Vendre', market_price: 'Prix du marché' },
        drawer: { primary_cert: 'Certification Primaire', advanced_cert: 'Certification Avancée', fund_pwd: 'Mots de passe Fonds', login_pwd: 'Mots de passe Connexion', email_auth: 'Auth Email', terms: 'Conditions', help: 'Aide', language: 'Langue', logout: 'Déconnexion' },
        mine: { security: 'Centre de Sécurité', identity: 'Vérification d\'Identité', payment: 'Méthodes de Paiement', language: 'Langue', help: 'Centre d\'Aide', about: 'À Propos', logout: 'Déconnexion', verified: 'Vérifié', unverified: 'Non Vérifié' },
        trade: {

            contract: 'Contrat', look_up: 'Monter', buy_long: 'Acheter Long', look_down: 'Descendre', buy_short: 'Acheter Court',
            duration: 'Durée', profit: 'Profit', amount: 'Montant', balance: 'Solde', confirm_order: 'Confirmer',
            spot_soon: 'Trading Spot (Bientôt)', buy: 'Acheter', sell: 'Vendre', avail: 'Disp', open_orders: 'Ordres Ouverts',
            no_open_orders: 'Aucun ordre', price: 'Prix', amt: 'Qté', limit_order: 'Ordre Limite', market_order: 'Ordre Marché'
        },
        assets: {

            deposit: 'Dépôt', withdraw: 'Retrait', chain_name: 'Nom Chaîne', deposit_addr: 'Adresse Dépôt',
            copy_addr: 'Copier', min_deposit: 'Dépôt Minimum', withdraw_addr: 'Adresse Retrait',
            paste_addr: 'Coller Adresse', fee: 'Frais', fund_pwd: 'Mots de passe Fonds', enter_pwd: 'Entrer 6 chiffres',
            total_assets: 'Actifs Totaux (USDT)', transfer: 'Transfert', asset: 'Actif', avail: 'Disp', frozen: 'Gelé',
            notice_1: 'Veuillez ne pas déposer d\'actifs autres que USDT.', notice_2: 'Dépôt minimum : 10 USDT.', min_withdraw: 'Retrait min',
            platform_assets: 'Actifs Plateforme', financial_assets: 'Actifs Financiers', contract_assets: 'Actifs Contrat', account_balance: 'Solde Compte',
            details: 'Détails Actifs', occupied: 'Occupé', equivalent: 'Équivalent (USDT)', swap: 'Échange'
        }
    },
    tw: {
        common: { confirm: '確定', cancel: '取消', copy: '複製', submit: '提交', all: '全部', no_data: '暫無數據', available: '可用' },
        home: { new_user_bonus: '新戶福利', sign_up_bonus: '註冊即送 100 USDT 券', seconds_contract: '秒合約', seconds_desc: '30秒高達90%收益', market_trends: '市場趨勢', hot: '熱門', forex: '外匯', gold: '黃金', global: '全球站', credit_score: '信用分', notice: '系統維護公告' },
        market: { title: '行情', vol: '成交量', high: '最高', low: '最低', change: '漲跌', markets: '行情', favorites: '自選', spot: '幣幣交易', futures: '合約', pair: '名稱', last_price: '最新價', chg: '漲跌幅', no_favorites: '暫無自選', seconds: '秒合約', cycle: '週期合約交易', search_coin: '搜索幣種' },
        login: { welcome: '歡迎回來', account: '賬號', username: '用戶名', username_req: '請輸入用戶名', password: '密碼', password_req: '請輸入密碼', btn: '登錄', forgot: '忘記密碼?', register: '註冊帳號' },
        grid: { mining: '質押挖礦', loan: '助力貸', swap: '閃兌', cycle: '週期合約', promotion: '推廣中心', spot: '幣幣交易', seconds: '秒合約', finance: '理財', ieo: 'IEO認購', deposit: '快捷充幣', withdraw: '快速提幣', support: '在線客服' },
        nav: { home: '首頁', market: '行情', trade: '交易', assets: '資產', mine: '我的' },
        cycle: { title: '週期', buy: '買漲', sell: '買跌', market_price: '市場價' },
        drawer: { primary_cert: '初級認證', advanced_cert: '高級認證', fund_pwd: '設置資金密碼', login_pwd: '設置登錄密碼', email_auth: '郵箱認證', terms: '服務條款', help: '幫助中心', language: '語言設置', logout: '退出登錄' },
        mine: { security: '安全中心', identity: '身份認證', payment: '收款方式', language: '語言設置', help: '幫助中心', about: '關於我們', logout: '退出登錄', verified: '已認證', unverified: '未認證' },
        trade: {

            contract: '合約', look_up: '看漲', buy_long: '買漲', look_down: '看跌', buy_short: '買跌',
            duration: '週期', profit: '收益', amount: '金額', balance: '餘額', confirm_order: '確認下單',
            spot_soon: '幣幣交易 (即將開放)', buy: '買入', sell: '賣出', avail: '可用', open_orders: '當前委託',
            no_open_orders: '暫無委託', price: '價格', amt: '數量', limit_order: '限價單', market_order: '市價單'
        },
        assets: {

            deposit: '充幣', withdraw: '提幣', chain_name: '鏈名稱', deposit_addr: '充幣地址',
            copy_addr: '複製地址', min_deposit: '最小充值金額', withdraw_addr: '提幣地址',
            paste_addr: '粘貼地址', fee: '手續費', fund_pwd: '資金密碼', enter_pwd: '輸入6位密碼',
            total_assets: '總資產 (USDT)', transfer: '劃轉', asset: '幣種', avail: '可用', frozen: '凍結',
            notice_1: '請勿充值非USDT資產到此地址。', notice_2: '最小充值金額: 10 USDT。', min_withdraw: '最小提幣',
            platform_assets: '平臺資產', financial_assets: '理財資產', contract_assets: '合約資產', account_balance: '賬戶餘額',
            details: '資產明細', occupied: '佔用', equivalent: '折合 (USDT)', swap: '閃兌'
        }
    }
}

const storedLang = localStorage.getItem('language') || 'en'

const i18n = createI18n({
    legacy: false,
    locale: storedLang,
    fallbackLocale: 'en',
    messages,
})

export default i18n

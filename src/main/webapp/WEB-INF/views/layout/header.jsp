<%-- Shared navigation header - included in all pages --%>

<link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700&display=swap" rel="stylesheet">
<style>
    /* ==================== GLOBAL RESET & BASE STYLES ==================== */
    *, *::before, *::after {
        margin: 0;
        padding: 0;
        box-sizing: border-box;
    }

    body {
        font-family: 'Inter', -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, sans-serif;
        background: #0f172a;
        color: #e2e8f0;
        min-height: 100vh;
        line-height: 1.6;
    }

    /* ==================== NAVIGATION BAR ==================== */
    .navbar {
        background: linear-gradient(135deg, #1e293b 0%, #0f172a 100%);
        border-bottom: 1px solid rgba(99, 102, 241, 0.2);
        padding: 0 2rem;
        display: flex;
        align-items: center;
        justify-content: space-between;
        height: 64px;
        position: sticky;
        top: 0;
        z-index: 100;
        backdrop-filter: blur(12px);
        box-shadow: 0 4px 30px rgba(0, 0, 0, 0.3);
    }

    .navbar-brand {
        display: flex;
        align-items: center;
        gap: 0.6rem;
        text-decoration: none;
        font-size: 1.35rem;
        font-weight: 700;
        color: #f1f5f9;
        letter-spacing: -0.02em;
    }

    .navbar-brand .icon {
        font-size: 1.6rem;
    }

    .navbar-brand span {
        background: linear-gradient(135deg, #818cf8, #a78bfa);
        -webkit-background-clip: text;
        -webkit-text-fill-color: transparent;
        background-clip: text;
    }

    .nav-links {
        display: flex;
        gap: 0.5rem;
        list-style: none;
    }

    .nav-links a {
        text-decoration: none;
        color: #94a3b8;
        font-weight: 500;
        font-size: 0.9rem;
        padding: 0.5rem 1rem;
        border-radius: 8px;
        transition: all 0.25s ease;
    }

    .nav-links a:hover,
    .nav-links a.active {
        color: #f1f5f9;
        background: rgba(99, 102, 241, 0.15);
    }

    /* ==================== MAIN CONTAINER ==================== */
    .container {
        max-width: 1200px;
        margin: 2rem auto;
        padding: 0 2rem;
    }

    /* ==================== PAGE HEADER ==================== */
    .page-header {
        display: flex;
        justify-content: space-between;
        align-items: center;
        margin-bottom: 2rem;
    }

    .page-header h1 {
        font-size: 1.75rem;
        font-weight: 700;
        color: #f1f5f9;
        letter-spacing: -0.02em;
    }

    .page-header h1 .accent {
        background: linear-gradient(135deg, #818cf8, #a78bfa);
        -webkit-background-clip: text;
        -webkit-text-fill-color: transparent;
        background-clip: text;
    }

    /* ==================== FLASH MESSAGES ==================== */
    .alert {
        padding: 0.85rem 1.25rem;
        border-radius: 10px;
        margin-bottom: 1.5rem;
        font-size: 0.9rem;
        font-weight: 500;
        display: flex;
        align-items: center;
        gap: 0.5rem;
        animation: slideDown 0.3s ease;
    }

    @keyframes slideDown {
        from { opacity: 0; transform: translateY(-10px); }
        to   { opacity: 1; transform: translateY(0); }
    }

    .alert-success {
        background: rgba(34, 197, 94, 0.12);
        border: 1px solid rgba(34, 197, 94, 0.3);
        color: #86efac;
    }

    .alert-error {
        background: rgba(239, 68, 68, 0.12);
        border: 1px solid rgba(239, 68, 68, 0.3);
        color: #fca5a5;
    }

    /* ==================== DATA TABLE ==================== */
    .data-table-wrapper {
        background: linear-gradient(145deg, #1e293b, #1a2332);
        border-radius: 16px;
        border: 1px solid rgba(99, 102, 241, 0.12);
        overflow: hidden;
        box-shadow: 0 8px 32px rgba(0, 0, 0, 0.3);
    }

    .data-table {
        width: 100%;
        border-collapse: collapse;
    }

    .data-table thead th {
        background: rgba(99, 102, 241, 0.08);
        color: #a5b4fc;
        font-weight: 600;
        font-size: 0.78rem;
        text-transform: uppercase;
        letter-spacing: 0.06em;
        padding: 1rem 1.25rem;
        text-align: left;
        border-bottom: 1px solid rgba(99, 102, 241, 0.15);
    }

    .data-table tbody td {
        padding: 0.9rem 1.25rem;
        font-size: 0.9rem;
        color: #cbd5e1;
        border-bottom: 1px solid rgba(255, 255, 255, 0.04);
        transition: background 0.2s ease;
    }

    .data-table tbody tr:hover td {
        background: rgba(99, 102, 241, 0.06);
    }

    .data-table tbody tr:last-child td {
        border-bottom: none;
    }

    /* ==================== BUTTONS ==================== */
    .btn {
        display: inline-flex;
        align-items: center;
        gap: 0.4rem;
        padding: 0.55rem 1.1rem;
        border-radius: 8px;
        font-size: 0.85rem;
        font-weight: 600;
        text-decoration: none;
        border: none;
        cursor: pointer;
        transition: all 0.25s ease;
        font-family: inherit;
    }

    .btn-primary {
        background: linear-gradient(135deg, #6366f1, #8b5cf6);
        color: #fff;
        box-shadow: 0 4px 14px rgba(99, 102, 241, 0.35);
    }

    .btn-primary:hover {
        transform: translateY(-1px);
        box-shadow: 0 6px 20px rgba(99, 102, 241, 0.5);
    }

    .btn-edit {
        background: rgba(59, 130, 246, 0.12);
        color: #93c5fd;
        border: 1px solid rgba(59, 130, 246, 0.25);
    }

    .btn-edit:hover {
        background: rgba(59, 130, 246, 0.22);
        color: #bfdbfe;
    }

    .btn-delete {
        background: rgba(239, 68, 68, 0.1);
        color: #fca5a5;
        border: 1px solid rgba(239, 68, 68, 0.2);
    }

    .btn-delete:hover {
        background: rgba(239, 68, 68, 0.2);
        color: #fecaca;
    }

    .btn-secondary {
        background: rgba(100, 116, 139, 0.15);
        color: #94a3b8;
        border: 1px solid rgba(100, 116, 139, 0.25);
    }

    .btn-secondary:hover {
        background: rgba(100, 116, 139, 0.25);
        color: #cbd5e1;
    }

    .actions-cell {
        display: flex;
        gap: 0.5rem;
    }

    /* ==================== FORM STYLES ==================== */
    .form-card {
        background: linear-gradient(145deg, #1e293b, #1a2332);
        border-radius: 16px;
        border: 1px solid rgba(99, 102, 241, 0.12);
        padding: 2rem;
        max-width: 640px;
        box-shadow: 0 8px 32px rgba(0, 0, 0, 0.3);
    }

    .form-grid {
        display: grid;
        grid-template-columns: 1fr 1fr;
        gap: 1.25rem;
    }

    .form-group {
        display: flex;
        flex-direction: column;
        gap: 0.4rem;
    }

    .form-group.full-width {
        grid-column: 1 / -1;
    }

    .form-group label {
        font-size: 0.82rem;
        font-weight: 600;
        color: #a5b4fc;
        text-transform: uppercase;
        letter-spacing: 0.04em;
    }

    .form-group input,
    .form-group select {
        background: rgba(15, 23, 42, 0.6);
        border: 1px solid rgba(99, 102, 241, 0.2);
        border-radius: 8px;
        padding: 0.7rem 0.9rem;
        color: #e2e8f0;
        font-size: 0.9rem;
        font-family: inherit;
        transition: border-color 0.25s ease, box-shadow 0.25s ease;
        outline: none;
    }

    .form-group input:focus,
    .form-group select:focus {
        border-color: #818cf8;
        box-shadow: 0 0 0 3px rgba(99, 102, 241, 0.15);
    }

    .form-group input::placeholder {
        color: #475569;
    }

    .form-actions {
        display: flex;
        gap: 0.75rem;
        margin-top: 1.5rem;
        grid-column: 1 / -1;
    }

    /* ==================== BADGE ==================== */
    .badge {
        display: inline-block;
        padding: 0.2rem 0.6rem;
        border-radius: 6px;
        font-size: 0.75rem;
        font-weight: 600;
        background: rgba(99, 102, 241, 0.12);
        color: #a5b4fc;
        border: 1px solid rgba(99, 102, 241, 0.2);
    }

    /* ==================== ERROR PAGE ==================== */
    .error-card {
        background: linear-gradient(145deg, #1e293b, #1a2332);
        border-radius: 16px;
        border: 1px solid rgba(239, 68, 68, 0.2);
        padding: 3rem;
        text-align: center;
        max-width: 500px;
        margin: 4rem auto;
    }

    .error-card .error-icon {
        font-size: 3rem;
        margin-bottom: 1rem;
    }

    .error-card h2 {
        color: #fca5a5;
        margin-bottom: 0.75rem;
    }

    .error-card p {
        color: #94a3b8;
        margin-bottom: 1.5rem;
    }

    /* ==================== VALIDATION ERRORS ==================== */
    .field-error {
        color: #fca5a5;
        font-size: 0.78rem;
        margin-top: 0.2rem;
    }

    .form-group input.error,
    .form-group select.error {
        border-color: rgba(239, 68, 68, 0.5);
    }

    /* ==================== EMPTY STATE ==================== */
    .empty-state {
        text-align: center;
        padding: 3rem;
        color: #64748b;
    }

    .empty-state .icon {
        font-size: 2.5rem;
        margin-bottom: 0.75rem;
    }

    /* ==================== RESPONSIVE ==================== */
    @media (max-width: 768px) {
        .navbar {
            padding: 0 1rem;
        }
        .container {
            padding: 0 1rem;
        }
        .form-grid {
            grid-template-columns: 1fr;
        }
        .page-header {
            flex-direction: column;
            gap: 1rem;
            align-items: flex-start;
        }
        .data-table-wrapper {
            overflow-x: auto;
        }
    }
</style>

<!-- Navigation Bar -->
<nav class="navbar">
    <a href="${pageContext.request.contextPath}/" class="navbar-brand">
        <span class="icon">&#128218;</span>
        <span>University Bookstore</span>
    </a>
    <ul class="nav-links">
        <li><a href="${pageContext.request.contextPath}/books">&#128214; Books</a></li>
        <li><a href="${pageContext.request.contextPath}/authors">&#9997; Authors</a></li>
    </ul>
</nav>

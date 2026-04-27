<%-- 科目一覧JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">
    <c:param name="title">
        得点管理システム
    </c:param>

    <c:param name="scripts"></c:param>

    <c:param name="content">
        <section class="me-4">
            <!-- ① 画面タイトル -->
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
                科目管理
            </h2>

            <!-- ② 新規登録リンク -->
            <div class="my-2 text-end px-4">
                <a href="SubjectCreate.action">新規登録</a>
            </div>

            <!-- ③ 科目一覧 -->
            <c:choose>
                <c:when test="${subjects.size() > 0}">
                    <div class="px-4">
                        検索結果：${subjects.size()}件
                    </div>

                    <table class="table table-hover">
                        <tr>
                            <!-- ④ ヘッダ（科目コード） -->
                            <th>科目コード</th>
                            <!-- ⑤ ヘッダ（科目名） -->
                            <th>科目名</th>
                            <th></th>
                            <th></th>
                        </tr>

                        <c:forEach var="subject" items="${subjects}">
                            <tr>
                                <!-- ⑥ 科目情報（科目コード） -->
                                <td>${subject.subjectCode}</td>

                                <!-- ⑦ 科目情報（科目名） -->
                                <td>${subject.subjectName}</td>

                                <!-- ⑧ 科目情報変更リンク -->
                                <td>
                                    <a href="SubjectUpdate.action?code=${subject.subjectCode}">
                                        変更
                                    </a>
                                </td>

                                <!-- ⑨ 科目情報削除リンク -->
                                <td>
                                    <a href="SubjectDelete.action?code=${subject.subjectCode}">
                                        削除
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </c:when>

                <c:otherwise>
                    <div class="px-4">
                        科目情報が存在しませんでした。
                    </div>
                </c:otherwise>
            </c:choose>

        </section>
    </c:param>
</c:import>
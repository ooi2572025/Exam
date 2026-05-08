<%-- 成績管理一覧JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>
    <c:param name="scripts"></c:param>
    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">成績管理</h2>

            <%-- 検索フォーム --%>
            <form method="get" action="TestRegist.action">
                <div class="row border mx-3 mb-3 py-2 align-items-center rounded">

                    <div class="col">
                        <label class="form-label">入学年度</label>
                        <select class="form-select" name="f1">
                            <option value="0">--------</option>
                            <c:forEach var="year" items="${entYearList}">
                                <option value="${year}"
                                    <c:if test="${year == f1}">selected</c:if>
                                >${year}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="col">
                        <label class="form-label">クラス</label>
                        <select class="form-select" name="f2">
                            <option value="0">--------</option>
                            <c:forEach var="classNum" items="${classNumList}">
                                <option value="${classNum}"
                                    <c:if test="${classNum == f2}">selected</c:if>
                                >${classNum}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="col">
                        <label class="form-label">科目</label>
                        <select class="form-select" name="f3">
                            <option value="0">--------</option>
                            <c:forEach var="subject" items="${subjectList}">
                                <option value="${subject.cd}"
                                    <c:if test="${subject.cd == f3}">selected</c:if>
                                >${subject.name}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="col">
                        <label class="form-label">回数</label>
                        <select class="form-select" name="f4">
                            <option value="0">--------</option>
                            <c:forEach var="no" items="${noList}">
                                <option value="${no}"
                                    <c:if test="${no == f4}">selected</c:if>
                                >${no}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="col-auto mt-4">
                        <button class="btn btn-secondary" type="submit">検索</button>
                    </div>

                </div>
            </form>

            <%-- 成績一覧 --%>
            <c:if test="${not empty tests}">
                <form action="TestRegistExecute.action" method="post">

                    <div class="mx-3 mb-2">科目：${subjectName}（${f4}回）</div>

                    <table class="table table-hover">
                        <tr>
                            <th>入学年度</th>
                            <th>クラス</th>
                            <th>学生番号</th>
                            <th>氏名</th>
                            <th>点数</th>
                        </tr>
                        <c:forEach var="test" items="${tests}">
                            <tr>
                                <td>${test.entYear}</td>
                                <td>${test.classNum}</td>
                                <td>${test.studentNo}</td>
                                <td>${test.studentName}</td>
                                <td>
                                    <input type="text" class="form-control"
                                           name="point_${test.studentNo}"
                                           value="${test.points}"
                                           maxlength="3"
                                           style="width:80px;">
                                    <c:if test="${not empty errors[test.studentNo]}">
                                        <div class="text-warning small">${errors[test.studentNo]}</div>
                                    </c:if>
                                </td>
                            </tr>
                            <input type="hidden" name="regist" value="${test.studentNo}">
                            <input type="hidden" name="class_${test.studentNo}" value="${test.classNum}">
                        </c:forEach>
                    </table>

                    <input type="hidden" name="subject" value="${f3}">
                    <input type="hidden" name="count" value="${f4}">
                    <input type="hidden" name="f1" value="${f1}">
                    <input type="hidden" name="f2" value="${f2}">

                    <div class="mx-3 mb-3">
                        <button class="btn btn-secondary" type="submit">登録して終了</button>
                    </div>

                </form>
            </c:if>

        </section>
    </c:param>
</c:import>